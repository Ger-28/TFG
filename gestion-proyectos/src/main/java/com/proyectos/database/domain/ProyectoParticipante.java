package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Relación entre proyectos y participantes.
 */
@Entity
@Table(name = "proyecto_participante")
public class ProyectoParticipante extends AbstractEntity<ProyectoParticipanteId> {

    @EmbeddedId
    private ProyectoParticipanteId id;

    @ManyToOne(optional = false)
    @MapsId("proyectoId")
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    @ManyToOne(optional = false)
    @MapsId("participanteId")
    @JoinColumn(name = "id_participante")
    private Participante participante;

    @Override
    public @Nullable ProyectoParticipanteId getId() {
        return id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
