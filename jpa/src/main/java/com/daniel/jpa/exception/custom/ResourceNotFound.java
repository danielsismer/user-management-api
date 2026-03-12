package com.daniel.jpa.exception.custom;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message){
        super(message);
    }
}
