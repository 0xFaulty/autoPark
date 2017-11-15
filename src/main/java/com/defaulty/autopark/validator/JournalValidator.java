package com.defaulty.autopark.validator;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.Journal;
import com.defaulty.autopark.service.journal.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        try {
            journal.setTime_in(convertDate(journal.getTime_in_str()));

        }catch (ParseException e){
            errors.rejectValue("time_in_str", "Time.format");
        }
        try {
            journal.setTime_out(convertDate(journal.getTime_out_str()));
        }catch (ParseException e){
            errors.rejectValue("time_out_str", "Time.format");
        }

        journal.setAuto_str(convert(journal.getAuto_str()));
        journal.setRoute_str(convert(journal.getRoute_str()));
    }

    private String convert(String s){
        try {
            return RepairEncoding.repair(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date convertDate(String str) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        return format.parse(str);
    }

}

