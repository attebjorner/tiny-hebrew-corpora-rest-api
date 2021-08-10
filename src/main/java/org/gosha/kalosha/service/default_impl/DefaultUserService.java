package org.gosha.kalosha.service.default_impl;

import org.gosha.kalosha.dao.RoleDao;
import org.gosha.kalosha.dao.UserDao;
import org.gosha.kalosha.model.Role;
import org.gosha.kalosha.model.User;
import org.gosha.kalosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DefaultUserService implements UserService, UserDetailsService
{
    private final UserDao userDao;

    private final RoleDao roleDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder)
    {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userDao.getByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
