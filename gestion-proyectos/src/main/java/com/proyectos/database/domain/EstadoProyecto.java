package com.proyectos.database.domain;

import java.util.Arrays;

/**
 * Estado en el que se encuentra un proyecto.
 */
public enum EstadoProyecto {
    PREPARACION("Preparación"),
    PRESENTADO("Presentado"),
    NO_PRESENTADO("No presentado"),
    CONCEDIDO("Concedido"),
    NO_CONCEDIDO("No concedido");

    private final String databaseValue;

    EstadoProyecto(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    public String getDatabaseValue() {
        return databaseValue;
    }

    public static EstadoProyecto fromDatabaseValue(String value) {
        return Arrays.stream(values())
                .filter(estado -> estado.databaseValue.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado desconocido: " + value));
    }
}
