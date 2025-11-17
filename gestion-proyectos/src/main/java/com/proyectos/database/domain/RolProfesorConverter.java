package com.proyectos.database.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RolProfesorConverter implements AttributeConverter<RolProfesor, String> {

    @Override
    public String convertToDatabaseColumn(RolProfesor attribute) {
        return attribute == null ? null : attribute.getDatabaseValue();
    }

    @Override
    public RolProfesor convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RolProfesor.fromDatabaseValue(dbData);
    }
}
