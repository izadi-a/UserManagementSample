package com.javasample.controller;

import java.util.List;

import com.javasample.error.UserNotFoundException;
import com.javasample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.javasample.model.User;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/users"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public List<User> firstPage() {
        return userService.getAllUsers();
    }

    @DeleteMapping(path = {"/{id}"})
    public void delete(@Valid @PathVariable("id") int id, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new UserNotFoundException(id);
        }
        userService.deleteUser(id);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        userService.insertUser(user);
        return user;
    }

}