package com.defaulty.autopark.dao.role;

import com.defaulty.autopark.model.Role;

import java.util.List;

public interface RoleRepositoryCustom {

    void addRole(Role role);

    void updateRole(Role auto);

    void removeRole(long id);

    Role getRoleById(long id);

    List<Role> listRoles();

}
