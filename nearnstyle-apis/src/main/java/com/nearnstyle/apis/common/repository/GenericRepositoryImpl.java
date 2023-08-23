package com.nearnstyle.apis.common.repository;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericRepositoryImpl {

    @PersistenceContext
    public EntityManager entityManager;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
