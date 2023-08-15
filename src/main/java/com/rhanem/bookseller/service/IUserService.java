package com.rhanem.bookseller.service;

import com.rhanem.bookseller.model.User;

import java.util.Optional;

public interface IUserService {

    User saveUser(User user);
    Optional<User> findByUsername(String username);
    void makeAdmin(String username);
}
