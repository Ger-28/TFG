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

    public static final int EVALUACION_MAX_LENGTH = 45;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_proyecto")
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

    @Convert(converter = RolProfesorConverter.class)
    @Column(name = "rol_profesor")
    private RolProfesor rolProfesor;

    @Convert(converter = EstadoProyectoConverter.class)
    @Column(name = "estado")
    private EstadoProyecto estado;

    @Column(name = "evaluacion", length = EVALUACION_MAX_LENGTH)
    @Size(max = EVALUACION_MAX_LENGTH)
    private String evaluacion;

    @ManyToOne
    @JoinColumn(name = "id_coordinador")
    private Participante coordinador;

    @ManyToOne
    @JoinColumn(name = "id_tecnico")
    private Usuario tecnico;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProyectoParticipante> participantes = new HashSet<>();

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

    public Participante getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Participante coordinador) {
        this.coordinador = coordinador;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public Set<ProyectoParticipante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<ProyectoParticipante> participantes) {
        this.participantes = participantes;
    }
}
