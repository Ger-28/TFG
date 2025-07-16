package com.proyectos.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta de la tabla proyecto_entidad.
 */
@Embeddable
public class ProyectoEntidadId implements Serializable {

    @Column(name = "proyecto_idproyecto")
    private Long proyectoId;

    @Column(name = "entidad_identidad")
    private Long entidadId;

    public ProyectoEntidadId() {}

    public ProyectoEntidadId(Long proyectoId, Long entidadId) {
        this.proyectoId = proyectoId;
        this.entidadId = entidadId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Long getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Long entidadId) {
        this.entidadId = entidadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProyectoEntidadId other)) return false;
        return Objects.equals(proyectoId, other.proyectoId) && Objects.equals(entidadId, other.entidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proyectoId, entidadId);
    }
}
