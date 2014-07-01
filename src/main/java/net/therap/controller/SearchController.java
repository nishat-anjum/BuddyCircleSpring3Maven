package net.therap.controller;

import net.therap.domain.User;
import net.therap.services.FriendService;
import net.therap.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/4/12
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private static final Logger log = LoggerFactory.getLogger(SearchController.class);
    private UserService userService;
    private FriendService friendService;

    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String sentFriendRequest(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");
        setModelAttributes(user, model);
        model.addAttribute("sentMessage", "Friend request has been sent");
        setModelAttributes(user, model);


        return "/friendSearchResult";

    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchFriend(HttpServletRequest request, @RequestParam(value = "searchFriend") String name, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");

        log.debug("Search Friend " + name);
        List<User> listOfUser = userService.getUserByName(name);
        List<User> listOfFriend = friendService.getAllFriends(user);
        List<User> searchResult = userService.getSearchResult(user, listOfFriend, listOfUser);
        setModelAttributes(user, model);
        if (searchResult.size() != 0) {
            model.addAttribute("searchResult", searchResult);

        } else {
            model.addAttribute("noSearchResult", "No result found by this name");
        }
        return "/friendSearchResult";
    }

    private void setModelAttributes(User user, Model model) {
        long numOfNewRequest = friendService.getNumberOfNewRequest(user);
        long numOfFriend = friendService.getNumberOfFriend(user);
        model.addAttribute("numOfNewRequest", numOfNewRequest);
        model.addAttribute("numOfFriends", numOfFriend);
        model.addAttribute("loggedUser", user);

    }
}
