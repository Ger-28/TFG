package com.proyectos.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para la tabla participante_departamento.
 */
@Embeddable
public class ParticipanteDepartamentoId implements Serializable {

    @Column(name = "id_departamento")
    private Long departamentoId;

    @Column(name = "id_participante")
    private Long participanteId;

    public ParticipanteDepartamentoId() {}

    public ParticipanteDepartamentoId(Long departamentoId, Long participanteId) {
        this.departamentoId = departamentoId;
        this.participanteId = participanteId;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteDepartamentoId other)) return false;
        return Objects.equals(departamentoId, other.departamentoId)
                && Objects.equals(participanteId, other.participanteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departamentoId, participanteId);
    }
}
