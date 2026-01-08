package com.app.kinlock.common.enums;

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
