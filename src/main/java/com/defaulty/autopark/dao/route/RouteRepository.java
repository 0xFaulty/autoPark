package com.defaulty.autopark.dao.route;

import com.defaulty.autopark.model.data.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long>, RouteRepositoryCustom {
    //Route findByName(String name);
}
