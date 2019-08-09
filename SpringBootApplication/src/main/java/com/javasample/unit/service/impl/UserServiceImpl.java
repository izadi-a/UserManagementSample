package com.javasample.unit.service.impl;

import com.javasample.model.User;
import com.javasample.repository.UserRepository;
import com.javasample.unit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @inheritDoc
 *
 * @author Izadi Ali
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService<User> {

    @Autowired
    UserRepository userRepository;

    /**
     * {@inheritDoc}
     *
     * @param user A user that should add.
     */
    @Override
    @Transactional
    public void create(User user) {
        userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     *
     * @return A list of users.
     */
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @param id A user's id.
     * @return A user by id.
     */
    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    /**
     * {@inheritDoc}
     *
     * @param id A user's id.
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param user An object of user.
     */
    @Override
    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

}
