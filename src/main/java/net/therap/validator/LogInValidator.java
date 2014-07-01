package net.therap.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/2/12
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogInValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "login.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.password");

        //To change body of implemented methods use File | Settings | File Templates.
    }
}
