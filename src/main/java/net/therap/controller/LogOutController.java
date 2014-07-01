package net.therap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/4/12
 * Time: 3:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/logOut")
public class LogOutController {

    @RequestMapping(method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        String str = "password.mismatch";
        return "redirect:/registration";

    }
}
