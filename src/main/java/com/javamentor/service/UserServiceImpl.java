package com.javamentor.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamentor.entity.User;
import com.javamentor.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl() {

    }

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
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
    public boolean deleteUserById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

}
