package com.javamentor.repository;

import com.javamentor.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getById(Long id) {
        return entityManager
                .find(Role.class, id);
    }

}
