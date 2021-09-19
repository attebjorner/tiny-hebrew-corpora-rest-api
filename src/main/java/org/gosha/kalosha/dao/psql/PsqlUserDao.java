package org.gosha.kalosha.dao.psql;

import org.gosha.kalosha.dao.UserDao;
import org.gosha.kalosha.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PsqlUserDao implements UserDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public PsqlUserDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getByUsername(String username)
    {
        return sessionFactory.getCurrentSession()
                .createQuery("from User u where u.username = :username ", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll()
    {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public User save(User user)
    {
        var session = sessionFactory.getCurrentSession();
        var id = (Long) session.save(user);
        return session.get(User.class, id);
    }

    @Override
    public void update(User user)
    {
        sessionFactory.getCurrentSession().update(user);
    }
}
