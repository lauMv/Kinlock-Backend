package com.app.kinlock.exceptions;

public class FunctionNotFoundException extends RuntimeException {
    public FunctionNotFoundException(String functionName) {
        super(String.format("Funcion con nombre %s no encontrada", functionName));
    }
}
