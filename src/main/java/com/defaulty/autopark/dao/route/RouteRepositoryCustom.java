package com.defaulty.autopark.dao.route;

import com.defaulty.autopark.model.Route;

import java.util.List;

public interface RouteRepositoryCustom {

    void addRoute(Route route);

    void updateRoute(Route auto);

    void removeRoute(long id);

    Route getRouteById(long id);

    List<Route> listRoutes();

}
