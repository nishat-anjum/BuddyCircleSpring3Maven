package net.therap.controller;

import net.therap.domain.Comment;
import net.therap.domain.Friends;
import net.therap.domain.User;
import net.therap.domain.WallPost;
import net.therap.services.FriendService;
import net.therap.services.PostService;
import net.therap.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: riffat
 * Date: 12/5/12
 * Time: 12:45 AM
 */
@Controller
@RequestMapping("/friends")
public class FriendController {
    private static final Logger log = LoggerFactory.getLogger(FriendController.class);

    public FriendService friendService;
    public UserService userService;
    public PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @RequestMapping(value = "/sendRequest/{friendId}", method = RequestMethod.GET)
    public String sendFriendRequest(@PathVariable(value = "friendId") int friendId, HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        User friends = userService.getUser(friendId);
        Friends newFriend = new Friends();
        newFriend.setFriend(friends);
        newFriend.setFriendOf(loggedUser);
        newFriend.setAcceptance(false);
        friendService.addNewFriend(newFriend);

        return "redirect:/search";

    }

    @RequestMapping(value = "/newRequests", method = RequestMethod.GET)
    public String showNewFriendRequest(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        log.debug("The user who Made Request " + user.getFirstName());
        List<User> newRequests = friendService.getNewRequests(user);
        long numOfNewRequest = newRequests.size();
        long numOfFriend = friendService.getNumberOfFriend(user);
        model.addAttribute("numOfNewRequest", numOfNewRequest);
        model.addAttribute("numOfFriends", numOfFriend);
        model.addAttribute("loggedUser", user);
        if (newRequests.size() != 0) {
            log.debug("0 no User " + newRequests.get(0).getFirstName());
            model.addAttribute("newRequests", newRequests);
        } else {
            model.addAttribute("noNewRequests", "There is no new friend request");
        }

        return "/showNewFriendRequests";
    }

    @RequestMapping(value = "/acceptRequest/{id}")
    public String acceptFriendRequest(HttpServletRequest request, @PathVariable(value = "id") int id) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        User friend = userService.getUser(id);
        log.debug("Accept Request From " + friend.getFirstName());
        friendService.acceptFriendRequest(user, friend);

        return "redirect:/friends/newRequests";
    }

    @RequestMapping(value = "/friendList")
    public String showFriendList(HttpServletRequest request, ModelMap model) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        List<User> friendsList = friendService.getAllFriends(user);
        log.debug("finally no of frnds" + friendsList.size());

        if (friendsList.size() != 0) {
            model.addAttribute("allFriendList", friendsList);
        } else {
            model.addAttribute("emptyFriendList", "No Friends in your list. Search friend and build your buddy Circle");
        }
        setModelAttributes(user, model);

        return "/friendList";
    }

    @RequestMapping(value = "/showFullProfile/{id}", method = RequestMethod.GET)
    public String showFullProfile(HttpServletRequest request, ModelMap model, @PathVariable(value = "id") int friendId) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        User friend = userService.getUser(friendId);
        WallPost wallPost = new WallPost();
        Comment comment = new Comment();
        setModelAttributes(user, model);
//        List<PostModel> friendsPost = postService.getPosts(friend);
        List<WallPost> friendsPost = postService.getPosts(friend);
        model.addAttribute("friendsPost", friendsPost);
        model.addAttribute("friend", friend);
        model.addAttribute("wallPost", wallPost);
        model.addAttribute("comment", comment);
        request.getSession().setAttribute("friend", friend);

        return "/friendProfile";

    }

    public void setModelAttributes(User user, ModelMap model) {
        long numOfNewRequest = friendService.getNumberOfNewRequest(user);
        long numOfFriend = friendService.getNumberOfFriend(user);
        model.addAttribute("numOfNewRequest", numOfNewRequest);
        model.addAttribute("numOfFriends", numOfFriend);
        model.addAttribute("loggedUser", user);
    }

    @RequestMapping(value = "/about/{id}", method = RequestMethod.GET)
    public String aboutFriend(@PathVariable(value = "id") int friendId, HttpServletRequest request, ModelMap model) {
        User friend = userService.getUser(friendId);
        User user = (User) request.getSession().getAttribute("loggedUser");
        model.addAttribute("loggedUser", user);
        model.addAttribute("user", friend);
        setModelAttributes(user, model);

        return "/userProfile";

    }

    @RequestMapping(value = "/unFriend/{id}", method = RequestMethod.GET)
    public String unFriend(@PathVariable(value = "id") int friendId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        User friend = userService.getUser(friendId);
        friendService.unFriend(user, friend);

        return "redirect:/friends/friendList";
    }
}
