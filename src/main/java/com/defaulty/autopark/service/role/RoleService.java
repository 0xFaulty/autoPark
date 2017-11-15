package com.defaulty.autopark.service.role;

import com.defaulty.autopark.model.Role;

import java.util.List;

public interface RoleService {

    void save(Role roles);

    void add(Role roles);

    void update(Role roles);

    void remove(long id);

    Role getById(long id);

    List<Role> list();

    Role convert(String str);

}
