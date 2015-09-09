package com.simple.plataforma.infraestructura.data.repository;


import com.simple.plataforma.dominio.modelo.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ecandela on 05/09/2015.
 */

public class CountryRepositoryImpl implements CountryRepositoryCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void clearEntityCache()
    {
        SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
        sessionFactory.getCache().evictEntityRegion(Country.class);
    }

}