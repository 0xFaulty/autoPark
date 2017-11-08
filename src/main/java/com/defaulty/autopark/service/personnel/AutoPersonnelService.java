package com.defaulty.autopark.service.personnel;

import com.defaulty.autopark.model.data.AutoPersonnel;

import java.util.List;

public interface AutoPersonnelService {

    void save(AutoPersonnel personnel);

    void add(AutoPersonnel personnel);

    void update(AutoPersonnel personnel);

    void remove(long id);

    AutoPersonnel getById(long id);

    List<AutoPersonnel> list();

}
