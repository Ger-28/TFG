package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Actividad planificada dentro de un paquete de trabajo.
 */
@Entity
@Table(name = "actividad")
public class Actividad extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_actividad")
    private Long id;

    @Column(name = "titulo", length = 45)
    @Size(max = 45)
    private String titulo;

    @Column(name = "descripcion", length = 200)
    @Size(max = 200)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "actividad_padre")
    private Actividad actividadPadre;

    @OneToMany(mappedBy = "actividadPadre")
    private Set<Actividad> subActividades = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_paquete")
    private PaqueteTrabajo paquete;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_responsable")
    private Participante responsable;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ActividadParticipante> participantes = new HashSet<>();

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entregable> entregables = new HashSet<>();

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Actividad getActividadPadre() {
        return actividadPadre;
    }

    public void setActividadPadre(Actividad actividadPadre) {
        this.actividadPadre = actividadPadre;
    }

    public Set<Actividad> getSubActividades() {
        return subActividades;
    }

    public void setSubActividades(Set<Actividad> subActividades) {
        this.subActividades = subActividades;
    }

    public PaqueteTrabajo getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteTrabajo paquete) {
        this.paquete = paquete;
    }

    public Participante getResponsable() {
        return responsable;
    }

    public void setResponsable(Participante responsable) {
        this.responsable = responsable;
    }

    public Set<ActividadParticipante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<ActividadParticipante> participantes) {
        this.participantes = participantes;
    }

    public Set<Entregable> getEntregables() {
        return entregables;
    }

    public void setEntregables(Set<Entregable> entregables) {
        this.entregables = entregables;
    }
}
