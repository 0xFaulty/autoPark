package com.defaulty.autopark.dao.journal;

import com.defaulty.autopark.model.Journal;

import java.util.List;

public interface JournalRepositoryCustom {

    void addJournal(Journal journal);

    void updateJournal(Journal journal);

    void removeJournal(long id);

    Journal getJournalById(long id);

    List<Journal> listJournals();

}
