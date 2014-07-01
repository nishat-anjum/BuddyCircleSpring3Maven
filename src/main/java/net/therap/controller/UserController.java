package net.therap.controller;

import net.therap.domain.User;
import net.therap.services.FriendService;
import net.therap.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/8/12
 * Time: 2:43 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    public FriendService friendService;
    public UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile(HttpServletRequest request, ModelMap model) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        User userInfo = userService.getUser(user.getUserId());
        model.addAttribute("loggedUser", userInfo);
        model.addAttribute("user", userInfo);
        setModelAttributes(user, model);
        model.addAttribute("profile", "myProfile");
        return "/userProfile";


    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editProfile(ModelMap model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        setModelAttributes(user, model);
        user = userService.getUser(user.getUserId());
        model.addAttribute("loggedUser", user);
        return "/editProfile";
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST )
    public String processProfileInformation(User user,@RequestParam(value = "imageContent",required = true)MultipartFile file, HttpServletRequest request ) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        user.setPassword(loggedUser.getPassword());
        user.setUserId(loggedUser.getUserId());
        user.setImageFileName(file.getOriginalFilename());
        user.setImageContentType(file.getContentType());
        try {
            user.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.updateUserInformation(user);
        request.getSession().setAttribute("loggedUser",user);
        return "redirect:/user/profile";
    }

    private void setModelAttributes(User user, ModelMap model) {

        long numOfNewRequest = friendService.getNumberOfNewRequest(user);
        long numOfFriend = friendService.getNumberOfFriend(user);
        model.addAttribute("numOfNewRequest", numOfNewRequest);
        model.addAttribute("numOfFriends", numOfFriend);

    }

    @RequestMapping(value = "/showProfile/{id}", method = RequestMethod.GET)
    public String showRequestingUserProfile(HttpServletRequest request, @PathVariable(value = "id") int userId, ModelMap model) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        User infoOf = userService.getUser(userId);
        model.addAttribute("loggedUser", user);
        model.addAttribute("user", infoOf);
        setModelAttributes(user, model);

        return "/userProfile";
    }
    @RequestMapping(value = "/about/{id}", method = RequestMethod.GET)
    public String aboutUser(HttpServletRequest request, @PathVariable(value = "id") int userId, ModelMap model) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        User infoOf = userService.getUser(userId);
        model.addAttribute("loggedUser", user);
        model.addAttribute("user", infoOf);
        setModelAttributes(user, model);
        model.addAttribute("sendFriendRequest" , "makeFriend");
        return "/userProfile";
    }
      @RequestMapping(value = "/showProfilePhoto/{id}" , method = RequestMethod.GET)
    public void showProfilePhoto(HttpServletResponse response, @PathVariable(value = "id") int id){
          log.debug("image streaming, id", id);

          User user = userService.getUser(id);
          OutputStream output = null;

          try {
              output = response.getOutputStream();
              response.setContentType(user.getImageContentType());
              //response.setContentType("image/jpeg");
              output.write(user.getImage());
              output.flush();
          } catch (IOException e) {
              e.printStackTrace();

          } finally {
              try {
                  output.close();
              } catch (IOException e) {

              }

          }
      }


}
