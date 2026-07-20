package com.app.kinlock.common.enums;

import com.app.kinlock.exceptions.EntityNotFoundException;
import lombok.Getter;

@Getter
public enum BenefitsCoverageEnum {
    PRINCIPALS("COBERTURAS PRINCIPALES"),
    ADDITIONALS("COBERTURAS ADICIONALES"),
    CLAUSES_AND_ANNEXES("CLAUSULAS Y ANEXOS");

    private final String value;

    BenefitsCoverageEnum(String value) {
        this.value = value;
    }

    public static BenefitsCoverageEnum fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La cobertura no puede estar vacia");
        }
        String normalized = value.trim();

        for (BenefitsCoverageEnum v : values()) {
            if (v.name().equalsIgnoreCase(normalized) || v.value.equalsIgnoreCase(normalized)) {
                return v;
            }
        }
        throw new EntityNotFoundException("Cobertura invalida: " + value);
    }
}