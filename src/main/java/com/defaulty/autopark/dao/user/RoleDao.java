package com.defaulty.autopark.dao.user;

import com.defaulty.autopark.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
