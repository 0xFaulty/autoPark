package com.defaulty.autopark.service.personnel;

import com.defaulty.autopark.dao.personnel.AutoPersonnelRepository;
import com.defaulty.autopark.model.AutoPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutoPersonnelServiceImpl implements AutoPersonnelService {

    @Autowired
    private AutoPersonnelRepository personnelDao;

    @Override
    public void save(AutoPersonnel personnel) {
        this.personnelDao.save(personnel);
    }

    @Override
    @Transactional
    public void add(AutoPersonnel personnel) {
        this.personnelDao.addAutoPersonnel((personnel));
    }

    @Override
    @Transactional
    public void update(AutoPersonnel personnel) {
        this.personnelDao.updateAutoPersonnel(personnel);
    }

    @Override
    @Transactional
    public void remove(long id) {
        this.personnelDao.removeAutoPersonnel(id);
    }

    @Override
    @Transactional
    public AutoPersonnel getById(long id) {
        return this.personnelDao.getAutoPersonnelById(id);
    }

    @Override
    @Transactional
    public List<AutoPersonnel> list() {
        return this.personnelDao.listAutoPersonnel();
    }

    @Override
    public AutoPersonnel convert(String str) {
        str = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
        Integer id = Integer.parseInt(str);
        return getById(id);
    }

}
