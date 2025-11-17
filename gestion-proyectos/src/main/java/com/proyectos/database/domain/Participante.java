package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

/**
 * Participante asociado a una entidad.
 */
@Entity
@Table(name = "participante")
public class Participante extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_participante")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    @Size(max = 50)
    @NotBlank
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_entidad")
    private Entidad entidad;

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

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
}
