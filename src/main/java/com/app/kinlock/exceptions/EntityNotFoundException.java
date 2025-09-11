package com.app.kinlock.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entity, Integer id) {

        super(String.format("entity.not.found.message", entity, id));
    }

}
