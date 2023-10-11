package com.example.bankingManagementSystem.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable t) {
        super(message, t);
    }

}
