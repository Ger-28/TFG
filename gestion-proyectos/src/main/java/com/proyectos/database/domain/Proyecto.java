package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Proyecto gestionado por la aplicación.
 */
@Entity
@Table(name = "proyecto")
public class Proyecto extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idproyecto")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    @Size(max = 100)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProyecto tipo;

    @Column(name = "acronimo", nullable = false, length = 20)
    @Size(max = 20)
    private String acronimo;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_profesor")
    private RolProfesor rolProfesor;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProyecto estado;

    @Column(name = "evaluacion", length = 45)
    @Size(max = 45)
    private String evaluacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idprofesor")
    private Profesor profesor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idtecnico")
    private Tecnico tecnico;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProyectoEntidad> entidades = new HashSet<>();

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

    public TipoProyecto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProyecto tipo) {
        this.tipo = tipo;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public RolProfesor getRolProfesor() {
        return rolProfesor;
    }

    public void setRolProfesor(RolProfesor rolProfesor) {
        this.rolProfesor = rolProfesor;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Set<ProyectoEntidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(Set<ProyectoEntidad> entidades) {
        this.entidades = entidades;
    }
}
