package com.defaulty.autopark.dao.role;

import com.defaulty.autopark.model.Role;
import com.defaulty.autopark.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {
    //Role findByName(String name);
}
