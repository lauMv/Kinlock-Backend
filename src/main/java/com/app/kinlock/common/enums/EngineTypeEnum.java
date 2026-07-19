package com.app.kinlock.common.enums;

import lombok.Getter;

@Getter
public enum EngineTypeEnum {
    COMBUSTION("COMBUSTION"),
    ELECTRIC("ELECTRICO"),
    HIBRID("HIBRID");

    private final String value;

    EngineTypeEnum(String value){this.value = value;}

    public static EngineTypeEnum fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String normalized = value.trim().toLowerCase();
        for (EngineTypeEnum type : values()) {
            if (type.name().equalsIgnoreCase(normalized) || type.value.equalsIgnoreCase(normalized)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de motor invalido: " + value);
    }
}
