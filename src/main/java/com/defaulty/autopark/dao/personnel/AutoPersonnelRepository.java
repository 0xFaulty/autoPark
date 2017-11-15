package com.defaulty.autopark.dao.personnel;

import com.defaulty.autopark.model.AutoPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoPersonnelRepository extends JpaRepository<AutoPersonnel, Long>, AutoPersonnelRepositoryCustom {
    //AutoPersonnel findByName(String name);
}
