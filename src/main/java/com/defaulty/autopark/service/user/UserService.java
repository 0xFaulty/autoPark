package com.defaulty.autopark.service.user;

import com.defaulty.autopark.model.Role;
import com.defaulty.autopark.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    void add(User user);

    void update(User user);

    void remove(long id);

    User getById(long id);

    List<User> list();

    void activateToggle(long id);

    boolean hasRole(User user, UserRoles userRoles);

}
