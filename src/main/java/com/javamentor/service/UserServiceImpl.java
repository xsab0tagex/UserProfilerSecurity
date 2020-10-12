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

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id);
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
    public boolean updateUser(User user) {
        try {
            repository.updateUser(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

}
