package com.defaulty.autopark.dao.auto;

import com.defaulty.autopark.model.data.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long>, AutoRepositoryCustom {
    //Auto findByName(String name);
}
