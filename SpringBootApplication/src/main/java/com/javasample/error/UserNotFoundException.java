package com.javasample.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User id not found : " + id);
    }
}
