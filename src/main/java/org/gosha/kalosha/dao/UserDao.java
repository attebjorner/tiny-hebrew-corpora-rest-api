package org.gosha.kalosha.dao;

import org.gosha.kalosha.model.User;

import java.util.List;

public interface UserDao
{
    User getByUsername(String username);

    List<User> getAll();

    User save(User user);

    void update(User user);
}
