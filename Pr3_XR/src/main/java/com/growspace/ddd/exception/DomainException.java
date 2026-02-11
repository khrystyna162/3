package com.growspace.ddd.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}