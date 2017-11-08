package com.defaulty.autopark.service.auto;

import com.defaulty.autopark.model.data.Auto;

import java.util.List;

public interface AutoService {

    void save(Auto auto);

    void add(Auto auto);

    void update(Auto auto);

    void remove(long id);

    Auto getById(long id);

    List<Auto> list();

}
