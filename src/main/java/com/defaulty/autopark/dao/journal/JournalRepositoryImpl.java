package com.defaulty.autopark.dao.journal;

import com.defaulty.autopark.model.Journal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JournalRepositoryImpl implements JournalRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(JournalRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addJournal(Journal journal) {
        entityManager.persist(journal);
        logger.info("Journal successfully saved. Journal details: " + journal);
    }

    @Override
    public void updateJournal(Journal journal) {
        entityManager.merge(journal);
        logger.info("Journal successfully update. Journal details: " + journal);
    }

    @Override
    public void removeJournal(long id) {
        Journal journal = entityManager.find(Journal.class, id);

        if (journal != null) {
            entityManager.remove(journal);
        }
        logger.info("Journal successfully removed. Journal details: " + journal);
    }

    @Override
    public Journal getJournalById(long id) {
        Journal journal = entityManager.find(Journal.class, id);
        logger.info("Journal successfully loaded. Journal details: " + journal);

        return journal;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Journal> listJournals() {
        List<Journal> journalList = entityManager.createQuery("from Journal").getResultList();
        for (Journal journal : journalList) {
            logger.info("Journal list: " + journal);
        }

        return journalList;
    }

}
