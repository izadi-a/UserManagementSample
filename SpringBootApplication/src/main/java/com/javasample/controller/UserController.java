package com.javasample.controller;

import java.util.List;

import com.javasample.error.UserNotFoundException;
import com.javasample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.javasample.model.User;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
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

    private final Logger LOG = LoggerFactory.getLogger(com.javasample.controller.UserController.class);

    @Autowired
    private UserService<User> userService;

    // =========================================== Find a user ==========================================

    /**
     * Finds a user by given id.
     *
     * @param id must not be {@literal null}.
     * @return A user
     * @since 1.0
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@Valid @PathVariable("id") int id){
        LOG.info("getting user with id: {}", id);
        User user = null;
        try{
            user = userService.findById(id);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
        }

        if (user == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    // =========================================== Get All Users ==========================================

    /**
     * Adds a given entity.
     *
     * @return A list of users
     * @since 1.0
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> firstPage() {
        LOG.info("getting all users");
        List<User> users = userService.getAll();

        if (users == null || users.isEmpty()) {
            LOG.info("no users found");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(users, HttpStatus.OK);
    }

    /**
     * Deletes a user.
     *
     * @param id must not be {@literal null}.
     * @throws UserNotFoundException
     * @since 1.0
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        LOG.info("deleting user with id: {}", id);
        User user = null;

        try{
            user = userService.findById(id);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
        }

        if (user == null) {
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // =========================================== Create New User ========================================

    /**
     * Creates a new user entity.
     *
     * @param user          must not be {@literal null}.
     * @throws UserNotFoundException
     * @since 1.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder){
        LOG.info("creating new user: {}", user);

        User oldUser = null;

        try{
        oldUser = userService.findById(user.getId());
        } catch (EntityNotFoundException e){
//            e.printStackTrace();
        }
        if (oldUser != null){
            LOG.info("a user with name " + user.getUserName() + " already exists");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        try{
            userService.create(user);
        } catch (Exception e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/users").buildAndExpand().toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    // =========================================== Update Existing User ===================================

    /**
     * Edit a user.
     *
     * @param user          must not be {@literal null}.
     * @return A list of users
     * @throws UserNotFoundException
     * @since 1.0
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user){
        LOG.info("updating user: {}", user);
        User currentUser = null;

        try{
            currentUser = userService.findById(id);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
        }

        if (currentUser == null){
            LOG.info("User with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setFamily(user.getFamily());
        currentUser.setUserName(user.getUserName());
        currentUser.setSalary(user.getSalary());

        userService.update(user);
        return new ResponseEntity(currentUser, HttpStatus.OK);
    }

}
