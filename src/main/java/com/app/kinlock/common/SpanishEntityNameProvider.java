package com.app.kinlock.common;

import java.util.Map;

public final class SpanishEntityNameProvider {

    private static final Map<String, String> MAP = Map.ofEntries(
            Map.entry("Department",     "Regional"),
            Map.entry("Vehicle",  "Vehiculo"),
            Map.entry("Insurance", "Aseguradora")
    );

    private SpanishEntityNameProvider() { }

    public static String getSpanishName(String entityName) {
        return MAP.getOrDefault(entityName, entityName);
    }
}
