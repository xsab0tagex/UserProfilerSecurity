package com.javamentor.service;

import java.util.List;

import com.javamentor.entity.User;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean saveUser(User user);

    boolean deleteUserById(Long id);

}
