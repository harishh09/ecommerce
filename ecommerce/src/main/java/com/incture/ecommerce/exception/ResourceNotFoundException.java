package com.incture.ecommerce.exception;
//This Exception is used when the product, order , user  is not found
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}