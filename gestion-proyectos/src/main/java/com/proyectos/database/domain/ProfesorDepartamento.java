package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Asociación entre profesores y departamentos.
 */
@Entity
@Table(name = "profesor_departamento")
public class ProfesorDepartamento extends AbstractEntity<ProfesorDepartamentoId> {

    @EmbeddedId
    private ProfesorDepartamentoId id;

    @ManyToOne(optional = false)
    @MapsId("departamentoId")
    @JoinColumn(name = "departamento_iddepartamento")
    private Departamento departamento;

    @ManyToOne(optional = false)
    @MapsId("profesorId")
    @JoinColumn(name = "profesor_idprofesor")
    private Profesor profesor;

    @Override
    public @Nullable ProfesorDepartamentoId getId() {
        return id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
