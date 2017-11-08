package com.defaulty.autopark.service.auto;

import com.defaulty.autopark.dao.auto.AutoRepository;
import com.defaulty.autopark.model.data.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    private AutoRepository autoDao;

    @Override
    public void save(Auto auto) {
        this.autoDao.save(auto);
    }

    @Override
    @Transactional
    public void add(Auto auto) {
        this.autoDao.addAuto((auto));
    }

    @Override
    @Transactional
    public void update(Auto auto) {
        this.autoDao.updateAuto(auto);
    }

    @Override
    @Transactional
    public void remove(long id) {
        this.autoDao.removeAuto(id);
    }

    @Override
    @Transactional
    public Auto getById(long id) {
        return this.autoDao.getAutoById(id);
    }

    @Override
    @Transactional
    public List<Auto> list() {
        return this.autoDao.listAutos();
    }

}
