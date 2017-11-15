package com.defaulty.autopark.dao.route;

import com.defaulty.autopark.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RouteRepositoryImpl implements RouteRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(RouteRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRoute(Route route) {
        entityManager.persist(route);
        logger.info("Route successfully saved. Route details: " + route);
    }

    @Override
    public void updateRoute(Route auto) {
        entityManager.merge(auto);
        logger.info("Route successfully update. Route details: " + auto);
    }

    @Override
    public void removeRoute(long id) {
        Route route = entityManager.find(Route.class, id);

        if (route != null) {
            entityManager.remove(route);
        }
        logger.info("Route successfully removed. Route details: " + route);
    }

    @Override
    public Route getRouteById(long id) {
        Route route = entityManager.find(Route.class, id);
        logger.info("Route successfully loaded. Route details: " + route);

        return route;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Route> listRoutes() {
        List<Route> routeList = entityManager.createQuery("from Route").getResultList();
        for (Route route : routeList) {
            logger.info("Route list: " + route);
        }

        return routeList;
    }

}
