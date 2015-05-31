package ru.test.vaadin.ui.validator;

import com.vaadin.data.Validator;

/**
 * Author: Georgy Gobozov
 * Date: 20.05.13
 */
public class NotEmptyValidator implements Validator {

    @Override
    public void validate(Object o) throws InvalidValueException {
        if (!isValid(o))
            throw new InvalidValueException("Field must be not empty!");
    }

    @Override
    public boolean isValid(Object o) {
        return (o != null || o.toString().trim().length() > 0);
    }
}
