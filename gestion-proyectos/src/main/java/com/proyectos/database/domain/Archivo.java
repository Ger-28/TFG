package com.proyectos.database.domain;

import com.proyectos.base.domain.AbstractEntity;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

/**
 * Archivo adjunto asociado a un entregable.
 */
@Entity
@Table(name = "archivo")
public class Archivo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_archivo")
    private Long id;

    @Lob
    @Column(name = "archivo_adjunto")
    private byte[] archivoAdjunto;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_entregable")
    private Entregable entregable;

    @Override
    public @Nullable Long getId() {
        return id;
    }

    public byte[] getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(byte[] archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public Entregable getEntregable() {
        return entregable;
    }

    public void setEntregable(Entregable entregable) {
        this.entregable = entregable;
    }
}
