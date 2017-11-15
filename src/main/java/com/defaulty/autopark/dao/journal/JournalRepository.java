package com.defaulty.autopark.dao.journal;

import com.defaulty.autopark.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long>, JournalRepositoryCustom {
    //Journal findByName(String name);
}
