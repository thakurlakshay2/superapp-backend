package com.example.superApp.payUrFren.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String entity, String field, Object value) {
        super(entity + " already exists with " + field + ": " + value);
    }
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
} 