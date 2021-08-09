package org.gosha.kalosha.service.default_impl;

import org.gosha.kalosha.dao.RoleDao;
import org.gosha.kalosha.dao.UserDao;
import org.gosha.kalosha.model.Role;
import org.gosha.kalosha.model.User;
import org.gosha.kalosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultUserService implements UserService
{
    private final UserDao userDao;

    private final RoleDao roleDao;

    @Autowired
    public DefaultUserService(UserDao userDao, RoleDao roleDao)
    {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User saveUser(User user)
    {
        return userDao.save(user);
    }

    @Override
    public Role saveRole(Role role)
    {
        return roleDao.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName)
    {
        User user = userDao.getByUsername(username);
        Role role = roleDao.getByName(roleName);
        user.getRoles().add(role);
        userDao.update(user);
    }

    @Override
    public User getUser(String username)
    {
        return userDao.getByUsername(username);
    }

    @Override
    public List<User> getUsers()
    {
        return userDao.getAll();
    }
}
