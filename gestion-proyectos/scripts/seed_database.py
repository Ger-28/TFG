#!/usr/bin/env python3
"""Populate the MySQL database with sample data for local development.

The script truncates the existing data in the tables that belong to the
application schema and inserts a coherent set of demo records. Connection
parameters can be configured through environment variables:

- DB_HOST (default: localhost)
- DB_PORT (default: 3306)
- DB_NAME (default: mydb)
- DB_USER (default: root)
- DB_PASSWORD (optional, el script pedirá la contraseña si no está definida)

Example usage:
    python scripts/seed_database.py
"""
from __future__ import annotations

import os
from getpass import getpass
from typing import List, Sequence

import mysql.connector
from mysql.connector import Binary, MySQLConnection



def _resolve_password() -> str:
    """Obtain the database password from the environment or by prompting."""

    env_password = os.getenv("DB_PASSWORD")
    if env_password:
        return env_password

    password = getpass(
        "Introduce manualmente la contraseña de la base de datos (requerido): "
    ).strip()
    if not password:
        raise RuntimeError(
            "Debes introducir la contraseña manualmente para ejecutar el script"
        )
    return password

DB_CONFIG = {
    "host": os.getenv("DB_HOST", "localhost"),
    "port": int(os.getenv("DB_PORT", "3306")),
    "database": os.getenv("DB_NAME", "mydb"),
    "user": os.getenv("DB_USER", "root"),
    "password": _resolve_password(),
    "charset": "utf8mb4",
    "use_unicode": True,
}


TABLE_TRUNCATE_ORDER: Sequence[str] = (
    "archivo",
    "entregable",
    "actividad_participante",
    "actividad",
    "paquete_participante",
    "paquete_trabajo",
    "proyecto_participante",
    "proyecto",
    "usuario",
    "servicio",
    "participante_departamento",
    "Departamento",
    "participante",
    "entidad",
)


def truncate_tables(connection: MySQLConnection) -> None:
    cursor = connection.cursor()
    cursor.execute("SET FOREIGN_KEY_CHECKS = 0")
    for table in TABLE_TRUNCATE_ORDER:
        cursor.execute(f"TRUNCATE TABLE {table}")
    cursor.execute("SET FOREIGN_KEY_CHECKS = 1")
    cursor.close()


def insert_entities(cursor) -> List[int]:
    entities = [
        ("Universidad de Granada", "España"),
        ("Universidad de Sevilla", "España"),
        ("CSIC", "España"),
    ]
    ids: List[int] = []
    for nombre, pais in entities:
        cursor.execute(
            "INSERT INTO entidad (nombre, pais) VALUES (%s, %s)", (nombre, pais)
        )
        ids.append(cursor.lastrowid)
    return ids


def insert_services(cursor) -> List[int]:
    services = [
        ("Transformación Digital"),
        ("Innovación Educativa"),
    ]
    ids: List[int] = []
    for nombre in services:
        cursor.execute("INSERT INTO servicio (nombre) VALUES (%s)", (nombre,))
        ids.append(cursor.lastrowid)
    return ids


def insert_users(cursor, service_ids: Sequence[int]) -> List[int]:
    users = [
        (
            "Ana Torres",
            "ana.torres@ugr.es",
            "$2a$12$ck8VZrGcE9VQ4q5VgR1JIOF7NnV83ESpP4BHrpQ.f3m1vP3xodS6K",
            "ADMINISTRADOR",
            service_ids[0],
        ),
        (
            "Luis Martín",
            "luis.martin@ugr.es",
            "$2a$12$22LCnPNblxU1Zkye6xAsNeuIl1t4Y8Kwj74D7b3tPRXokQqRy0eYC",
            "TECNICO",
            service_ids[0],
        ),
        (
            "Claudia Pérez",
            "claudia.perez@us.es",
            "$2a$12$Qd6kdHpLInSdoU9QXMfKvOjs0t/K46aZY7Sq3L3c5RvKaZyZA/Nf2",
            "TECNICO",
            service_ids[1],
        ),
    ]
    ids: List[int] = []
    for nombre, email, password, rol, servicio_id in users:
        cursor.execute(
            """
            INSERT INTO usuario
                (nombre, email, pass_hash, rol, id_servicio)
            VALUES (%s, %s, %s, %s, %s)
            """,
            (nombre, email, password, rol, servicio_id),
        )
        ids.append(cursor.lastrowid)
    return ids


def insert_participants(cursor, entity_ids: Sequence[int]) -> List[int]:
    participants = [
        ("Dra. Marta Ruiz", entity_ids[0]),
        ("Dr. Pablo Gómez", entity_ids[1]),
        ("Dra. Elena Soto", entity_ids[2]),
        ("Dr. Javier Cano", entity_ids[0]),
    ]
    ids: List[int] = []
    for nombre, entidad_id in participants:
        cursor.execute(
            "INSERT INTO participante (nombre, id_entidad) VALUES (%s, %s)",
            (nombre, entidad_id),
        )
        ids.append(cursor.lastrowid)
    return ids


def insert_departments(cursor, entity_ids: Sequence[int]) -> List[int]:
    departments = [
        ("Escuela Superior de Ingenierías", "ESCUELA", entity_ids[0]),
        ("Facultad de Ciencias", "FACULTAD", entity_ids[1]),
    ]
    ids: List[int] = []
    for nombre, tipo, entidad_id in departments:
        cursor.execute(
            """
            INSERT INTO Departamento (nombre, tipo_entidad, id_entidad)
            VALUES (%s, %s, %s)
            """,
            (nombre, tipo, entidad_id),
        )
        ids.append(cursor.lastrowid)
    return ids


def link_participants_departments(cursor, participant_ids, department_ids):
    relations = [
        (department_ids[0], participant_ids[0]),
        (department_ids[0], participant_ids[3]),
        (department_ids[1], participant_ids[1]),
        (department_ids[1], participant_ids[2]),
    ]
    cursor.executemany(
        """
        INSERT INTO participante_departamento (id_departamento, id_participante)
        VALUES (%s, %s)
        """,
        relations,
    )


def insert_projects(cursor, participant_ids, user_ids) -> List[int]:
    projects = [
        (
            "Plataforma de Gestión Colaborativa",
            "CBHE",
            "COLLAB",
            "Coordinador",
            "Concedido",
            "Muy positiva",
            participant_ids[0],
            user_ids[1],
        ),
        (
            "Red de Laboratorios Inteligentes",
            "PI",
            "SMARTLAB",
            "Socio",
            "Preparación",
            "Pendiente de revisión",
            participant_ids[1],
            user_ids[2],
        ),
    ]
    ids: List[int] = []
    for (
        titulo,
        tipo,
        acronimo,
        rol_profesor,
        estado,
        evaluacion,
        id_coordinador,
        id_tecnico,
    ) in projects:
        cursor.execute(
            """
            INSERT INTO proyecto (
                titulo, tipo, acronimo, rol_profesor, estado, evaluacion,
                id_coordinador, id_tecnico
            ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
            """,
            (
                titulo,
                tipo,
                acronimo,
                rol_profesor,
                estado,
                evaluacion,
                id_coordinador,
                id_tecnico,
            ),
        )
        ids.append(cursor.lastrowid)
    return ids


def link_projects_participants(cursor, project_ids, participant_ids):
    relations = [
        (participant_ids[1], project_ids[0]),
        (participant_ids[2], project_ids[0]),
        (participant_ids[0], project_ids[1]),
        (participant_ids[3], project_ids[1]),
    ]
    cursor.executemany(
        """
        INSERT INTO proyecto_participante (id_participante, id_proyecto)
        VALUES (%s, %s)
        """,
        relations,
    )


def insert_work_packages(
    cursor,
    project_ids: Sequence[int],
    entity_ids: Sequence[int],
    participant_ids: Sequence[int],
) -> List[int]:
    work_packages = [
        (
            "Analítica de datos",
            "2024-01-15",
            "2024-06-30",
            "Integración de dashboards",
            entity_ids[0],
            entity_ids[1],
            participant_ids[2],
            project_ids[0],
        ),
        (
            "Sensórica distribuida",
            "2024-03-01",
            "2024-09-30",
            "Prototipo de laboratorio remoto",
            entity_ids[2],
            entity_ids[0],
            participant_ids[3],
            project_ids[1],
        ),
    ]
    ids: List[int] = []
    for (
        titulo,
        fecha_inicio,
        fecha_fin,
        milestones,
        id_entidad_lider,
        id_entidad_colider,
        id_participante_responsable,
        id_proyecto,
    ) in work_packages:
        cursor.execute(
            """
            INSERT INTO paquete_trabajo (
                titulo, fecha_inicio, fecha_fin, milestones,
                id_entidad_lider, id_entidad_colider,
                id_participante_responsable, id_proyecto
            ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
            """,
            (
                titulo,
                fecha_inicio,
                fecha_fin,
                milestones,
                id_entidad_lider,
                id_entidad_colider,
                id_participante_responsable,
                id_proyecto,
            ),
        )
        ids.append(cursor.lastrowid)
    return ids


def link_work_packages_participants(cursor, package_ids, participant_ids):
    relations = [
        (package_ids[0], participant_ids[0]),
        (package_ids[0], participant_ids[2]),
        (package_ids[1], participant_ids[1]),
        (package_ids[1], participant_ids[3]),
    ]
    cursor.executemany(
        """
        INSERT INTO paquete_participante (id_paquete, id_participante)
        VALUES (%s, %s)
        """,
        relations,
    )


def insert_activities(cursor, package_ids, participant_ids) -> List[int]:
    activities = [
        (
            "Definición de KPIs",
            "Diseño de métricas para evaluar el uso de la plataforma",
            None,
            package_ids[0],
            participant_ids[0],
        ),
        (
            "Implementación de nodos IoT",
            "Despliegue de sensores en laboratorios asociados",
            None,
            package_ids[1],
            participant_ids[3],
        ),
    ]
    ids: List[int] = []
    for (
        titulo,
        descripcion,
        actividad_padre,
        id_paquete,
        id_responsable,
    ) in activities:
        cursor.execute(
            """
            INSERT INTO actividad (
                titulo, `descripción`, actividad_padre, id_paquete, id_responsable
            ) VALUES (%s, %s, %s, %s, %s)
            """,
            (titulo, descripcion, actividad_padre, id_paquete, id_responsable),
        )
        ids.append(cursor.lastrowid)
    return ids


def link_activities_participants(cursor, activity_ids, participant_ids):
    relations = [
        (activity_ids[0], participant_ids[2]),
        (activity_ids[0], participant_ids[0]),
        (activity_ids[1], participant_ids[1]),
        (activity_ids[1], participant_ids[3]),
    ]
    cursor.executemany(
        """
        INSERT INTO actividad_participante (id_actividad, id_participante)
        VALUES (%s, %s)
        """,
        relations,
    )


def insert_deliverables(cursor, activity_ids, participant_ids) -> List[int]:
    deliverables = [
        (
            "Documento de KPIs",
            "Listado con definiciones y responsables",
            "2024-04-15",
            participant_ids[0],
            activity_ids[0],
        ),
        (
            "Informe de despliegue IoT",
            "Inventario de nodos, firmware y métricas iniciales",
            "2024-07-20",
            participant_ids[3],
            activity_ids[1],
        ),
    ]
    ids: List[int] = []
    for nombre, descripcion, fecha, participante_id, actividad_id in deliverables:
        cursor.execute(
            """
            INSERT INTO entregable (
                nombre, descripcion, fecha_entrega, id_participante, id_actividad
            ) VALUES (%s, %s, %s, %s, %s)
            """,
            (nombre, descripcion, fecha, participante_id, actividad_id),
        )
        ids.append(cursor.lastrowid)
    return ids


def insert_files(cursor, deliverable_ids):
    files = [
        (
            deliverable_ids[0],
            Binary(b"Resumen PDF KPIs"),
        ),
        (
            deliverable_ids[1],
            Binary(b"Plano nodos IoT"),
        ),
    ]
    for entregable_id, blob in files:
        cursor.execute(
            """
            INSERT INTO archivo (id_entregable, archivo_adjunto)
            VALUES (%s, %s)
            """,
            (entregable_id, blob),
        )


def main():
    connection = mysql.connector.connect(**DB_CONFIG)
    try:
        truncate_tables(connection)
        cursor = connection.cursor()
        connection.start_transaction()

        entity_ids = insert_entities(cursor)
        service_ids = insert_services(cursor)
        user_ids = insert_users(cursor, service_ids)
        participant_ids = insert_participants(cursor, entity_ids)
        department_ids = insert_departments(cursor, entity_ids)
        link_participants_departments(cursor, participant_ids, department_ids)
        project_ids = insert_projects(cursor, participant_ids, user_ids)
        link_projects_participants(cursor, project_ids, participant_ids)
        package_ids = insert_work_packages(cursor, project_ids, entity_ids, participant_ids)
        link_work_packages_participants(cursor, package_ids, participant_ids)
        activity_ids = insert_activities(cursor, package_ids, participant_ids)
        link_activities_participants(cursor, activity_ids, participant_ids)
        deliverable_ids = insert_deliverables(cursor, activity_ids, participant_ids)
        insert_files(cursor, deliverable_ids)

        connection.commit()
        print("Datos de ejemplo insertados correctamente.")
    except Exception as exc:  # pragma: no cover - script level safeguard
        connection.rollback()
        raise exc
    finally:
        cursor.close()
        connection.close()


if __name__ == "__main__":
    main()
