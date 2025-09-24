package com.app.kinlock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleClassification {
    CAR("AUTOMOVIL"),
    SUV("VAGONETA"),
    PICKUP("CAMIONETA"),
    JEEP("JEEP"),
    VAN("FURGONETA"),
    TRUCK("CAMION"),
    MOTORCYCLE("MOTOCICLETA");

    private final String value;

    public static VehicleClassification fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String normalized = value.trim().toLowerCase();

        for (VehicleClassification v : values()) {
            if (v.name().equalsIgnoreCase(normalized) || v.value.equalsIgnoreCase(normalized)) {
                return v;
            }
        }
        return null;
    }
}
