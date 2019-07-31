package com.javasample.service;

import com.javasample.model.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    void deleteUser(Integer id);

    void updateUser(User user);
}
