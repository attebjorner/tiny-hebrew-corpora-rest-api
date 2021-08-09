package org.gosha.kalosha.dao;

import org.gosha.kalosha.model.Role;

public interface RoleDao
{
    Role getByName(String name);

    Role save(Role role);
}
