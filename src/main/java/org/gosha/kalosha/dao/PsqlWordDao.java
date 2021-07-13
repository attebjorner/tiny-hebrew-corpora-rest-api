package org.gosha.kalosha.dao;

import org.gosha.kalosha.models.Word;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PsqlWordDao implements WordDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public PsqlWordDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Word getById(long id)
    {
        return sessionFactory.getCurrentSession().get(Word.class, id);
    }
}
