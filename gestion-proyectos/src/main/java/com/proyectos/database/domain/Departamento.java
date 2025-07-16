package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

/**
 * Departamento dentro de una escuela.
 */
@Entity
@Table(name = "departamento")
public class Departamento extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iddepartamento")
    private Long id;

    @Column(name = "nombre", length = 75)
    @Size(max = 75)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "escuela_idescuela")
    private Escuela escuela;

    @Override
    public @Nullable Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
}
