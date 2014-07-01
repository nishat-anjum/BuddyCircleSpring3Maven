package net.therap.controller;

import net.therap.domain.User;
import net.therap.services.UserService;
import net.therap.validator.LogInValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 11/27/12
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/login")
public class LogInController {
    private static final Logger log = LoggerFactory.getLogger(LogInController.class);
    private LogInValidator logInValidator;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLogInValidator(LogInValidator logInValidator) {
        this.logInValidator = logInValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String logInForm(ModelMap model) {
        User user = new User();
        model.addAttribute("loggedUser", user);
        return "/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String logInProcess(User user, BindingResult result, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.logInValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("loggedUser",user);
        } else {
            User varifiedUser = userService.getUserByEmail(user.getEmail());
            if (varifiedUser != null && varifiedUser.getPassword().equals(user.getPassword())) {
                session.setAttribute("loggedUser", varifiedUser);
                return "redirect:/posts";
            } else {
                model.addAttribute("loggedUser", user);
                model.addAttribute("invalidUser" , "Enter valid Email Id and correct Password");
            }
        }
        return "/login";

    }
}
