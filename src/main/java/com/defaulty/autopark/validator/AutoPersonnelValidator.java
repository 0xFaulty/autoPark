package com.defaulty.autopark.validator;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.data.AutoPersonnel;
import com.defaulty.autopark.service.personnel.AutoPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;

@Component
public class AutoPersonnelValidator implements Validator {

    @Autowired
    private AutoPersonnelService autoPersonnelService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutoPersonnel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AutoPersonnel autoPersonnel = (AutoPersonnel) o;

        autoPersonnel.setFirst_name(convert(autoPersonnel.getFirst_name()));
        autoPersonnel.setLast_name(convert(autoPersonnel.getLast_name()));
        autoPersonnel.setFather_name(convert(autoPersonnel.getFather_name()));

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

