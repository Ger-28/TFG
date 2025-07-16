package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Relación entre proyectos y entidades colaboradoras.
 */
@Entity
@Table(name = "proyecto_entidad")
public class ProyectoEntidad extends AbstractEntity<ProyectoEntidadId> {

    @EmbeddedId
    private ProyectoEntidadId id;

    @ManyToOne(optional = false)
    @MapsId("proyectoId")
    @JoinColumn(name = "proyecto_idproyecto")
    private Proyecto proyecto;

    @ManyToOne(optional = false)
    @MapsId("entidadId")
    @JoinColumn(name = "entidad_identidad")
    private Entidad entidad;

    @Override
    public @Nullable ProyectoEntidadId getId() {
        return id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
}
