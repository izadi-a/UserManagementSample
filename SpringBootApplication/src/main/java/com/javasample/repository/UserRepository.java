package com.javasample.repository;

import com.javasample.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for CRUD operations on a repository for user.
 *
 * @author Izadi Ali
 * @version 1.0
 * @since 1.0
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
