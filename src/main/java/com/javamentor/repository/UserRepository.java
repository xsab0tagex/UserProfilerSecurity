package com.javamentor.repository;

import com.javamentor.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    void updateUser(User user);
}