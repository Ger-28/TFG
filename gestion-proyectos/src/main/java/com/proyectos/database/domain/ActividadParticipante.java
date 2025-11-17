package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Asociación entre actividades y participantes.
 */
@Entity
@Table(name = "actividad_participante")
public class ActividadParticipante extends AbstractEntity<ActividadParticipanteId> {

    @EmbeddedId
    private ActividadParticipanteId id;

    @ManyToOne(optional = false)
    @MapsId("actividadId")
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    @ManyToOne(optional = false)
    @MapsId("participanteId")
    @JoinColumn(name = "id_participante")
    private Participante participante;

    @Override
    public @Nullable ActividadParticipanteId getId() {
        return id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
