package com.proyectos.database.domain;

import java.util.Arrays;

/**
 * Roles que puede desempeñar un profesor dentro de un proyecto.
 */
public enum RolProfesor {
    COORDINADOR("Coordinador"),
    SOCIO("Socio");

    private final String databaseValue;

    RolProfesor(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    public String getDatabaseValue() {
        return databaseValue;
    }

    public static RolProfesor fromDatabaseValue(String value) {
        return Arrays.stream(values())
                .filter(rol -> rol.databaseValue.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rol desconocido: " + value));
    }
}
