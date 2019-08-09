package com.javasample.unit.service;

import com.javasample.error.UserNotFoundException;

import java.util.List;

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
    void create(T entity);

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
     * @return the user with the given id or null
     * @throws UserNotFoundException
     * @since 1.0
     */
    T findById(Integer id);

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
