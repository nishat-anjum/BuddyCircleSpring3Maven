package net.therap.controller;

import net.therap.domain.User;
import net.therap.services.UserService;
import net.therap.validator.RegistrationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 11/27/12
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/home")
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    UserService userService;
    private RegistrationValidator registrationValidator;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setRegistrationValidator(RegistrationValidator registrationValidator) {
        this.registrationValidator = registrationValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String loadRegistrationForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "/home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistrationInformation(User user, BindingResult result, Model model) {
        registrationValidator.validate(user, result);
        if (result.hasErrors()) {
                model.addAttribute("user", user);
        } else {
            userService.saveNewUser(user);
            model.addAttribute("user",new User());
            model.addAttribute("registrationSuccess" , "You have been registered successfully");
        }
        return "/home";

    }


}
