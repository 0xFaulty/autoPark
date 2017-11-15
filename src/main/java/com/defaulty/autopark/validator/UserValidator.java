package com.defaulty.autopark.validator;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.User;
import com.defaulty.autopark.service.user.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        validateUsername(user, errors);
        validateNewPassword(user, errors);
        validateConfirmPassword(user, errors);
        validateAttributes(user, errors);
    }

    public void validateAttributes(User user, Errors errors) {
        validateFirstname(user, errors);
        validateLastname(user, errors);
        validateEmailAddress(user, errors);
        validateBirthday(user, errors);
    }

    public boolean validateActivity(String username) {
        User user = userService.findByUsername(username);

        if (user != null)
            if (user.isActive()) return true;

        return false;
    }

    public void validateFirstname(User user, Errors errors) {
        user.setFirstname(convert(user.getFirstname()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "Required");
        if (user.getFirstname().length() > 32) {
            errors.rejectValue("firstname", "Size.userForm.firstname");
        }
    }

    public void validateLastname(User user, Errors errors) {
        user.setLastname(convert(user.getLastname()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "Required");
        if (user.getLastname().length() > 32) {
            errors.rejectValue("lastname", "Size.userForm.lastname");
        }
    }

    public void validateEmailAddress(User user, Errors errors) {
        user.setEmail(convert(user.getEmail()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");

        if (!EmailValidator.getInstance(true).isValid(user.getEmail()))
            errors.rejectValue("email", "Wrong.userForm.email");
    }

    public void validateBirthday(User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdayString", "Required");

        String DATE_FORMAT = "yyyy-MM-dd";

        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
            user.setBirthday(fmt.parseDateTime(user.getBirthdayString()).toDate());
        } catch (Exception e) {
            errors.rejectValue("birthdayString", "Wrong.userForm.birthday");
        }
    }

    public void validateUsername(User user, Errors errors) {
        user.setUsername(convert(user.getUsername()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 5 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
    }

    public void validateNewPassword(User user, Errors errors) {
        user.setNewPassword(convert(user.getNewPassword()));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "Required");
        if (user.getNewPassword().length() < 5 || user.getNewPassword().length() > 32) {
            errors.rejectValue("newPassword", "Size.userForm.password");
        }
    }

    public void validateConfirmPassword(User user, Errors errors) {
        user.setConfirmPassword(convert(user.getConfirmPassword()));

        if (!user.getConfirmPassword().equals(user.getNewPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
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

