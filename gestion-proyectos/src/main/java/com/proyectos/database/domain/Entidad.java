package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

/**
 * Entidad colaboradora de proyectos.
 */
@Entity
@Table(name = "entidad")
public class Entidad extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "identidad")
    private Long id;

    @Column(name = "nombre", length = 45)
    @Size(max = 45)
    private String nombre;

    @Column(name = "pais", length = 45)
    @Size(max = 45)
    private String pais;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
