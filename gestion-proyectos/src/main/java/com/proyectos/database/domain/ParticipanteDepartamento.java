package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Asociación entre participantes y departamentos.
 */
@Entity
@Table(name = "participante_departamento")
public class ParticipanteDepartamento extends AbstractEntity<ParticipanteDepartamentoId> {

    @EmbeddedId
    private ParticipanteDepartamentoId id;

    @ManyToOne(optional = false)
    @MapsId("departamentoId")
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(optional = false)
    @MapsId("participanteId")
    @JoinColumn(name = "id_participante")
    private Participante participante;

    @Override
    public @Nullable ParticipanteDepartamentoId getId() {
        return id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
