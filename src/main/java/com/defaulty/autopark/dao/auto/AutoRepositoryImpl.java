package com.defaulty.autopark.dao.auto;

import com.defaulty.autopark.model.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AutoRepositoryImpl implements AutoRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(AutoRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addAuto(Auto auto) {
        entityManager.persist(auto);
        logger.info("Auto successfully saved. Auto details: " + auto);
    }

    @Override
    public void updateAuto(Auto auto) {
        entityManager.merge(auto);
        logger.info("Auto successfully update. Auto details: " + auto);
    }

    @Override
    public void removeAuto(long id) {
        Auto auto = entityManager.find(Auto.class, id);

        if (auto != null) {
            entityManager.remove(auto);
        }
        logger.info("Auto successfully removed. Auto details: " + auto);
    }

    @Override
    public Auto getAutoById(long id) {
        Auto auto = entityManager.find(Auto.class, id);
        logger.info("Auto successfully loaded. Auto details: " + auto);

        return auto;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Auto> listAutos() {
        List<Auto> autoList = entityManager.createQuery("from Auto").getResultList();
        for (Auto auto : autoList) {
            logger.info("Auto list: " + auto);
        }

        return autoList;
    }

}
