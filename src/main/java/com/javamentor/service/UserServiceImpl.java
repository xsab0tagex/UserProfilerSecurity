package com.javamentor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamentor.entity.User;
import com.javamentor.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean saveUser(User user) {
        try {
            repository.save(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
        }

    }

}
