package com.javasample.controller;

import java.util.List;

import com.javasample.error.UserNotFoundException;
import com.javasample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.javasample.model.User;

import javax.validation.Valid;

/**
 * <h1>User Controller</h1>
 * <p>This is a controller for user's requests</p>
 *
 * @author Izadi Ali
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/users"})
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Adds a given entity.
     *
     * @return A list of users
     * @since 1.0
     */
    @GetMapping(produces = "application/json")
    public List<User> firstPage() {
        return userService.getAll();
    }

    /**
     * Deletes a user.
     *
     * @param id            must not be {@literal null}.
     * @param bindingResult must not be {@literal null}.
     * @throws UserNotFoundException
     * @since 1.0
     */
    @DeleteMapping(path = {"/{id}"})
    public void delete(@Valid @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException(id);
        }
        userService.delete(id);
    }

    /**
     * Creates a new user entity.
     *
     * @param user          must not be {@literal null}.
     * @param bindingResult must not be {@literal null}.
     * @throws UserNotFoundException
     * @since 1.0
     */
    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        userService.insert(user);
        return user;
    }

    /**
     * Edit a user.
     *
     * @param user          must not be {@literal null}.
     * @param bindingResult must not be {@literal null}.
     * @return A list of users
     * @throws UserNotFoundException
     * @since 1.0
     */
    @PostMapping({"/edit"})
    public User edit(@Valid @RequestBody User user, BindingResult bindingResult) {
        userService.update(user);
        return user;
    }

}