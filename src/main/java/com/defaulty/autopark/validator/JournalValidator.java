package com.defaulty.autopark.validator;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.data.Journal;
import com.defaulty.autopark.service.journal.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;

@Component
public class JournalValidator implements Validator {

    @Autowired
    private JournalService journalService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Journal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Journal journal = (Journal) o;

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

