package org.gosha.kalosha.dao.psql;

import org.gosha.kalosha.dao.RoleDao;
import org.gosha.kalosha.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PsqlRoleDao implements RoleDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public PsqlRoleDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getByName(String name)
    {
        return sessionFactory.getCurrentSession()
                .createQuery("from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Role save(Role role)
    {
        var session = sessionFactory.getCurrentSession();
        var id = (Long) session.save(role);
        return session.get(Role.class, id);
    }
}
