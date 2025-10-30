package com.app.kinlock.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entity, Integer id) {
        super(String.format("La entidad %s con id %s no fue encontrado", entity, id));
    }

}
