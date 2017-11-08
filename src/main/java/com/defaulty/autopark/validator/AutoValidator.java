package com.defaulty.autopark.validator;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.data.Auto;
import com.defaulty.autopark.service.auto.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;

@Component
public class AutoValidator implements Validator {

    @Autowired
    private AutoService autoService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Auto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Auto auto = (Auto) o;

        validateNumber(auto, errors);
        validateColor(auto, errors);
        validateMark(auto, errors);
    }

    private void validateNumber(Auto auto, Errors errors) {
        auto.setNum(convert(auto.getNum()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "num", "Required");
        if (auto.getNum().length() != 6) {
            errors.rejectValue("num", "Size.autoForm.number");
        }

    }

    private void validateColor(Auto auto, Errors errors) {
        auto.setColor(convert(auto.getColor()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "Required");
    }

    private void validateMark(Auto auto, Errors errors) {
        auto.setMark(convert(auto.getMark()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mark", "Required");
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

