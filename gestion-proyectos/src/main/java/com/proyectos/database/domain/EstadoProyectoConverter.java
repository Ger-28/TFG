package com.proyectos.database.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoProyectoConverter implements AttributeConverter<EstadoProyecto, String> {

    @Override
    public String convertToDatabaseColumn(EstadoProyecto attribute) {
        return attribute == null ? null : attribute.getDatabaseValue();
    }

    @Override
    public EstadoProyecto convertToEntityAttribute(String dbData) {
        return dbData == null ? null : EstadoProyecto.fromDatabaseValue(dbData);
    }
}
