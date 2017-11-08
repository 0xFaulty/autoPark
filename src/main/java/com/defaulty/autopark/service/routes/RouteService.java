package com.defaulty.autopark.service.routes;

import com.defaulty.autopark.model.data.Route;

import java.util.List;

public interface RouteService {

    void save(Route routes);

    void add(Route routes);

    void update(Route routes);

    void remove(long id);

    Route getById(long id);

    List<Route> list();

}
