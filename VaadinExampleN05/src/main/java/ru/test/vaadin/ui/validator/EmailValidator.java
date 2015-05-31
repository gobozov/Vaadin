package ru.test.vaadin.ui.validator;

import com.vaadin.data.Validator;

import java.util.regex.Pattern;

/**
 * Author: Georgy Gobozov
 * Date: 20.05.13
 */
public class EmailValidator implements Validator {


    @Override
    public void validate(Object o) throws InvalidValueException {
        if (!isValid(o))
            throw new InvalidValueException("Field must be not empty!");
    }

    @Override
    public boolean isValid(Object o) {
        String email_pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-.]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        return pattern.matcher(o.toString()).matches();
    }
}
