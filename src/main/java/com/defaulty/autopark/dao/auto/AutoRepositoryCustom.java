package com.defaulty.autopark.dao.auto;

import com.defaulty.autopark.model.data.Auto;

import java.util.List;

public interface AutoRepositoryCustom {

    void addAuto(Auto auto);

    void updateAuto(Auto auto);

    void removeAuto(long id);

    Auto getAutoById(long id);

    List<Auto> listAutos();

}
