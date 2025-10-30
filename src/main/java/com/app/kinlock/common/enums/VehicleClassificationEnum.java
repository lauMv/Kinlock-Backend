package com.app.kinlock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleClassificationEnum {
    CAR("AUTOMOVIL"),
    SUV("VAGONETA"),
    PICKUP("CAMIONETA"),
    JEEP("JEEP"),
    VAN("FURGONETA"),
    TRUCK("CAMION"),
    MOTORCYCLE("MOTOCICLETA");

    private final String value;

    public static VehicleClassificationEnum fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String normalized = value.trim().toUpperCase();

        for (VehicleClassificationEnum v : values()) {
            if (v.name().equalsIgnoreCase(normalized) || v.value.equalsIgnoreCase(normalized)) {
                return v;
            }
        }
        return null;
    }
}
