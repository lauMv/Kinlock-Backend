package com.app.kinlock.exceptions;

public class HasAssociatedEntityException extends RuntimeException {

    public HasAssociatedEntityException(String message) {
        super(message);
    }

    public HasAssociatedEntityException(String entity, String key) {
        super(String.format("has.assosiated.entity.message", entity, key));
    }
}

