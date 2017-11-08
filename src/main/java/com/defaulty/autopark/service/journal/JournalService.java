package com.defaulty.autopark.service.journal;

import com.defaulty.autopark.model.data.Journal;

import java.util.List;

public interface JournalService {

    void save(Journal journal);

    void add(Journal journal);

    void update(Journal journal);

    void remove(long id);

    Journal getById(long id);

    List<Journal> list();

}
