package com.defaulty.autopark.dao.personnel;

import com.defaulty.autopark.model.AutoPersonnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AutoPersonnelRepositoryImpl implements AutoPersonnelRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(AutoPersonnelRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addAutoPersonnel(AutoPersonnel autoPersonnel) {
        entityManager.persist(autoPersonnel);
        logger.info("Auto Personnel successfully saved. Auto Personnel details: " + autoPersonnel);
    }

    @Override
    public void updateAutoPersonnel(AutoPersonnel autoPersonnel) {
        entityManager.merge(autoPersonnel);
        logger.info("Auto Personnel successfully update. Auto Personnel details: " + autoPersonnel);
    }

    @Override
    public void removeAutoPersonnel(long id) {
        AutoPersonnel autoPersonnel = entityManager.find(AutoPersonnel.class, id);

        if (autoPersonnel != null) {
            entityManager.remove(autoPersonnel);
        }
        logger.info("Auto Personnel successfully removed. Auto Personnel details: " + autoPersonnel);
    }

    @Override
    public AutoPersonnel getAutoPersonnelById(long id) {
        AutoPersonnel autoPersonnel = entityManager.find(AutoPersonnel.class, id);
        logger.info("Auto Personnel successfully loaded. Auto Personnel details: " + autoPersonnel);

        return autoPersonnel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AutoPersonnel> listAutoPersonnel() {
        List<AutoPersonnel> autoPersonnelList = entityManager.createQuery("from AutoPersonnel").getResultList();
        for (AutoPersonnel autoPersonnel : autoPersonnelList) {
            logger.info("Auto Personnel list: " + autoPersonnel);
        }

        return autoPersonnelList;
    }

}
