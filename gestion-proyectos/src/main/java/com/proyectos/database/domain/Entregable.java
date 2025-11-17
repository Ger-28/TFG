package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

import java.time.Instant;

/**
 * Entregable asociado a una actividad concreta.
 */
@Entity
@Table(name = "entregable")
public class Entregable extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_entregable")
    private Long id;

    @Column(name = "nombre", length = 45)
    @Size(max = 45)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    @Size(max = 200)
    private String descripcion;

    @Column(name = "fecha_entrega", nullable = false)
    private Instant fechaEntrega;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_participante")
    private Participante participante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    @OneToOne(mappedBy = "entregable", cascade = CascadeType.ALL, orphanRemoval = true)
    private Archivo archivo;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instant getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Instant fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }
}
