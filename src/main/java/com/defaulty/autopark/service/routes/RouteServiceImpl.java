package com.defaulty.autopark.service.routes;

import com.defaulty.autopark.dao.route.RouteRepository;
import com.defaulty.autopark.model.data.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeDao;

    @Override
    public void save(Route route) {
        this.routeDao.save(route);
    }

    @Override
    @Transactional
    public void add(Route route) {
        this.routeDao.addRoute((route));
    }

    @Override
    @Transactional
    public void update(Route route) {
        this.routeDao.updateRoute(route);
    }

    @Override
    @Transactional
    public void remove(long id) {
        this.routeDao.removeRoute(id);
    }

    @Override
    @Transactional
    public Route getById(long id) {
        return this.routeDao.getRouteById(id);
    }

    @Override
    @Transactional
    public List<Route> list() {
        return this.routeDao.listRoutes();
    }

    @Override
    public Route convert(String str) {
        str = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
        Integer id = Integer.parseInt(str);
        return getById(id);
    }

}
