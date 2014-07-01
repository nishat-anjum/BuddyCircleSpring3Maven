package net.therap.validator;


import net.therap.domain.User;
import net.therap.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/2/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */

public class RegistrationValidator implements Validator {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {


        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "email.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
        ValidationUtils.rejectIfEmpty(errors, "gender", "gender.required");
        ValidationUtils.rejectIfEmpty(errors, "confPassword", "confpassword.required");
        User usr = (User) o;
        if (!usr.getPassword().equals(usr.getConfPassword())) {
            errors.rejectValue("confPassword", "password.mismatch");
        }
        User user = userService.getUserByEmail(usr.getEmail());
        if (user != null) {
            errors.rejectValue("email", "already.exists.login.name");
        }

    }

}
