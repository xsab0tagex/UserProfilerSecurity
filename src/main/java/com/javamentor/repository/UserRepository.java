package com.javamentor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javamentor.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}