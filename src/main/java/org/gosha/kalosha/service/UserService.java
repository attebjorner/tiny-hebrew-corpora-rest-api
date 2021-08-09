package org.gosha.kalosha.service;

import org.gosha.kalosha.model.Role;
import org.gosha.kalosha.model.User;

import java.util.List;

public interface UserService
{
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
