package com.proyectos.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta de la tabla proyecto_participante.
 */
@Embeddable
public class ProyectoParticipanteId implements Serializable {

    @Column(name = "id_proyecto")
    private Long proyectoId;

    @Column(name = "id_participante")
    private Long participanteId;

    public ProyectoParticipanteId() {}

    public ProyectoParticipanteId(Long proyectoId, Long participanteId) {
        this.proyectoId = proyectoId;
        this.participanteId = participanteId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
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
        if (!(o instanceof ProyectoParticipanteId other)) return false;
        return Objects.equals(proyectoId, other.proyectoId) && Objects.equals(participanteId, other.participanteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proyectoId, participanteId);
    }
}
