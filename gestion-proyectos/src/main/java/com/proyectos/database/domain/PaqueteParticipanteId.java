package com.proyectos.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para la tabla paquete_participante.
 */
@Embeddable
public class PaqueteParticipanteId implements Serializable {

    @Column(name = "id_paquete")
    private Long paqueteId;

    @Column(name = "id_participante")
    private Long participanteId;

    public PaqueteParticipanteId() {}

    public PaqueteParticipanteId(Long paqueteId, Long participanteId) {
        this.paqueteId = paqueteId;
        this.participanteId = participanteId;
    }

    public Long getPaqueteId() {
        return paqueteId;
    }

    public void setPaqueteId(Long paqueteId) {
        this.paqueteId = paqueteId;
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
        if (!(o instanceof PaqueteParticipanteId other)) return false;
        return Objects.equals(paqueteId, other.paqueteId)
                && Objects.equals(participanteId, other.participanteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paqueteId, participanteId);
    }
}
