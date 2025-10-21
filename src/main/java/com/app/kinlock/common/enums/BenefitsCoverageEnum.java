package com.app.kinlock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BenefitsCoverageEnum {
    PRINCIPALS ("COBERTURAS_PRINCIPALES"),
    ADDITIONALS("COBERTURAS_ADICIONALES"),
    CLAUSES_AND_ANNEXES("CLAUSULAS_Y_ANEXOS");

    private final String value;

    public static BenefitsCoverageEnum fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String normalized = value.trim().toLowerCase();

        for (BenefitsCoverageEnum v : values()) {
            if (v.name().equalsIgnoreCase(normalized) || v.value.equalsIgnoreCase(normalized)) {
                return v;
            }
        }
        return null;
    }
}
