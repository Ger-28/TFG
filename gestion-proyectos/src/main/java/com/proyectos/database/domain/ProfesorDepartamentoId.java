package com.proyectos.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para la tabla profesor_departamento.
 */
@Embeddable
public class ProfesorDepartamentoId implements Serializable {

    @Column(name = "departamento_iddepartamento")
    private Long departamentoId;

    @Column(name = "profesor_idprofesor")
    private Long profesorId;

    public ProfesorDepartamentoId() {}

    public ProfesorDepartamentoId(Long departamentoId, Long profesorId) {
        this.departamentoId = departamentoId;
        this.profesorId = profesorId;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfesorDepartamentoId other)) return false;
        return Objects.equals(departamentoId, other.departamentoId)
                && Objects.equals(profesorId, other.profesorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departamentoId, profesorId);
    }
}
