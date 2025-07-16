package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

/**
 * Técnico que puede participar en proyectos.
 */
@Entity
@Table(name = "tecnico")
public class Tecnico extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idtecnico")
    private Long id;

    @Column(name = "nombre", length = 50)
    @Size(max = 50)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "servicio_idservicio")
    private Servicio servicio;

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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
