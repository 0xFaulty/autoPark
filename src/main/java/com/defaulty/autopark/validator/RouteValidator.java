package com.defaulty.autopark.validator;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.data.Route;
import com.defaulty.autopark.service.routes.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;

@Component
public class RouteValidator implements Validator {

    @Autowired
    private RouteService routeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Route.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Route route = (Route) o;

        route.setName(convert(route.getName()));

    }

    private String convert(String s){
        try {
            return RepairEncoding.repair(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

