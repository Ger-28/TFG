package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Relación entre paquetes de trabajo y participantes.
 */
@Entity
@Table(name = "paquete_participante")
public class PaqueteParticipante extends AbstractEntity<PaqueteParticipanteId> {

    @EmbeddedId
    private PaqueteParticipanteId id;

    @ManyToOne(optional = false)
    @MapsId("paqueteId")
    @JoinColumn(name = "id_paquete")
    private PaqueteTrabajo paquete;

    @ManyToOne(optional = false)
    @MapsId("participanteId")
    @JoinColumn(name = "id_participante")
    private Participante participante;

    @Override
    public @Nullable PaqueteParticipanteId getId() {
        return id;
    }

    public PaqueteTrabajo getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteTrabajo paquete) {
        this.paquete = paquete;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
