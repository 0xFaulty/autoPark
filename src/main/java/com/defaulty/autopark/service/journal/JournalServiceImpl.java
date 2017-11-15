package com.defaulty.autopark.service.journal;

import com.defaulty.autopark.dao.journal.JournalRepository;
import com.defaulty.autopark.model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalDao;

    @Override
    public void save(Journal journal) {
        this.journalDao.save(journal);
    }

    @Override
    @Transactional
    public void add(Journal journal) {
        this.journalDao.addJournal((journal));
    }

    @Override
    @Transactional
    public void update(Journal journal) {
        this.journalDao.updateJournal(journal);
    }

    @Override
    @Transactional
    public void remove(long id) {
        this.journalDao.removeJournal(id);
    }

    @Override
    @Transactional
    public Journal getById(long id) {
        return this.journalDao.getJournalById(id);
    }

    @Override
    @Transactional
    public List<Journal> list() {
        return this.journalDao.listJournals();
    }

    @Override
    public Journal convert(String str) {
        str = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
        Integer id = Integer.parseInt(str);
        return getById(id);
    }

}
