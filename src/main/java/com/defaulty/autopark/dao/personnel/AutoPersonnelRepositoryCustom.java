package com.defaulty.autopark.dao.personnel;

import com.defaulty.autopark.model.data.AutoPersonnel;

import java.util.List;

public interface AutoPersonnelRepositoryCustom {

    void addAutoPersonnel(AutoPersonnel autoPersonnel);

    void updateAutoPersonnel(AutoPersonnel autoPersonnel);

    void removeAutoPersonnel(long id);

    AutoPersonnel getAutoPersonnelById(long id);

    List<AutoPersonnel> listAutoPersonnel();

}
