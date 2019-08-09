package com.javasample.service;

import com.javasample.error.UserNotFoundException;
import com.javasample.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for user service operations.
 *
 * @author Izadi Ali
 * @version 1.0
 * @since 1.0
 */
public interface UserService<T> {

    /**
     * Adds a given entity.
     *
     * @param entity must not be {@literal null}.
     * @since 1.0
     */
    void insert(T entity);

    /**
     * Retrieves all entities.
     *
     * @return A list of entity
     * @since 1.0
     */
    List<T> getAll();

    /**
     * Retrieves a user by its id.
     *
     * @param id must not be {@literal null}.
     * @return the user with the given id or {@literal Optional#empty()} if none found
     * @throws UserNotFoundException
     * @since 1.0
     */
    T getById(Integer id);

    /**
     * Deletes the given entity by id.
     *
     * @param id must not be {@literal null}.
     * @since 1.0
     */
    void delete(Integer id);

    /**
     * Updates the given entity.
     *
     * @param entity must not be {@literal null}.
     * @since 1.0
     */
    void update(T entity);
}
