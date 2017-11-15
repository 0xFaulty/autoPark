package com.defaulty.autopark.service.role;

import com.defaulty.autopark.dao.role.RoleRepository;
import com.defaulty.autopark.dao.role.RoleRepository;
import com.defaulty.autopark.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleDao;

    @Override
    public void save(Role role) {
        this.roleDao.save(role);
    }

    @Override
    @Transactional
    public void add(Role role) {
        this.roleDao.addRole((role));
    }

    @Override
    @Transactional
    public void update(Role role) {
        this.roleDao.updateRole(role);
    }

    @Override
    @Transactional
    public void remove(long id) {
        this.roleDao.removeRole(id);
    }

    @Override
    @Transactional
    public Role getById(long id) {
        return this.roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public List<Role> list() {
        return this.roleDao.listRoles();
    }

    @Override
    public Role convert(String str) {
        str = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
        Integer id = Integer.parseInt(str);
        return getById(id);
    }

}
