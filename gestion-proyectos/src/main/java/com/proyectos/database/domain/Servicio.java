package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

/**
 * Servicio al que pertenece un usuario.
 */
@Entity
@Table(name = "servicio")
public class Servicio extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_servicio")
    private Long id;

    @Column(name = "nombre", length = 100)
    @Size(max = 100)
    private String nombre;

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
}
