package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    User getCurrentUser();

    List<User> findAll();

    Optional<User> findByLogin(String login);

    void deleteById(Long id);

    Optional<User> findById(long id);

    User save(User old1);
}
