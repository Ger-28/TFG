package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Paquete de trabajo definido dentro de un proyecto.
 */
@Entity
@Table(name = "paquete_trabajo")
public class PaqueteTrabajo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_paquete")
    private Long id;

    @Column(name = "titulo", length = 45)
    @Size(max = 45)
    private String titulo;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @Column(name = "milestones", length = 200)
    @Size(max = 200)
    private String milestones;

    @ManyToOne
    @JoinColumn(name = "id_entidad_lider")
    private Entidad entidadLider;

    @ManyToOne
    @JoinColumn(name = "id_entidad_colider")
    private Entidad entidadColider;

    @ManyToOne
    @JoinColumn(name = "id_participante_responsable")
    private Participante participanteResponsable;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PaqueteParticipante> participantes = new HashSet<>();

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Actividad> actividades = new HashSet<>();

    @Override
    public @Nullable Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMilestones() {
        return milestones;
    }

    public void setMilestones(String milestones) {
        this.milestones = milestones;
    }

    public Entidad getEntidadLider() {
        return entidadLider;
    }

    public void setEntidadLider(Entidad entidadLider) {
        this.entidadLider = entidadLider;
    }

    public Entidad getEntidadColider() {
        return entidadColider;
    }

    public void setEntidadColider(Entidad entidadColider) {
        this.entidadColider = entidadColider;
    }

    public Participante getParticipanteResponsable() {
        return participanteResponsable;
    }

    public void setParticipanteResponsable(Participante participanteResponsable) {
        this.participanteResponsable = participanteResponsable;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Set<PaqueteParticipante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<PaqueteParticipante> participantes) {
        this.participantes = participantes;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }
}
