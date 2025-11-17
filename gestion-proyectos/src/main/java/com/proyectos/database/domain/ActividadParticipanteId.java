package com.proyectos.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para la tabla actividad_participante.
 */
@Embeddable
public class ActividadParticipanteId implements Serializable {

    @Column(name = "id_actividad")
    private Long actividadId;

    @Column(name = "id_participante")
    private Long participanteId;

    public ActividadParticipanteId() {}

    public ActividadParticipanteId(Long actividadId, Long participanteId) {
        this.actividadId = actividadId;
        this.participanteId = participanteId;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
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
        if (!(o instanceof ActividadParticipanteId other)) return false;
        return Objects.equals(actividadId, other.actividadId)
                && Objects.equals(participanteId, other.participanteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividadId, participanteId);
    }
}
