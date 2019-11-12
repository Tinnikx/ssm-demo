package com.taokaixiang.ssm.demo.v1.dao.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class MainRepository<T, ID> {

    private Class<T> entityClass = getType();

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public T findById(ID id) {
        return getSession().find(entityClass, id);
    }

    @Transactional
    public void create(T t) {
        getSession().save(t);
    }

    @Transactional
    public void deleteById(ID id) {
        getSession().delete(id);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected abstract Class<T> getType();
}
