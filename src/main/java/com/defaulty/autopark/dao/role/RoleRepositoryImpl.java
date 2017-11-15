package com.defaulty.autopark.dao.role;

import com.defaulty.autopark.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
        logger.info("Role successfully saved. Role details: " + role);
    }

    @Override
    public void updateRole(Role auto) {
        entityManager.merge(auto);
        logger.info("Role successfully update. Role details: " + auto);
    }

    @Override
    public void removeRole(long id) {
        Role role = entityManager.find(Role.class, id);

        if (role != null) {
            entityManager.remove(role);
        }
        logger.info("Role successfully removed. Role details: " + role);
    }

    @Override
    public Role getRoleById(long id) {
        Role role = entityManager.find(Role.class, id);
        logger.info("Role successfully loaded. Role details: " + role);

        return role;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listRoles() {
        List<Role> roleList = entityManager.createQuery("from Role").getResultList();
        for (Role role : roleList) {
            logger.info("Role list: " + role);
        }

        return roleList;
    }

}
