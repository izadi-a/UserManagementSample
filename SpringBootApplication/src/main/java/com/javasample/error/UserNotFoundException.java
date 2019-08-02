package com.javasample.error;

/**
 * Provides custom runtime exception.
 *
 * @author Izadi Ali
 * @version 1.0
 * @inheritDoc
 * @since 1.0
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(User Not Found)</tt>
     *
     * @param id the id of a user.
     * @since 1.0
     */
    public UserNotFoundException(Integer id) {
        super("User id not found : " + id);
    }
}
