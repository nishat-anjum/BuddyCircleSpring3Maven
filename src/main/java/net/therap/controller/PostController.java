package net.therap.controller;

import net.therap.domain.Comment;
import net.therap.domain.User;
import net.therap.domain.WallPost;
import net.therap.services.CommentService;
import net.therap.services.FriendService;
import net.therap.services.PostService;
import net.therap.services.UserService;
import net.therap.validator.PostValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * User: anjum
 * Date: 12/3/12
 * Time: 3:05 PM
 */
@Controller
@RequestMapping("/posts")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    public PostService postService;
    public FriendService friendService;
    public UserService userService;
    public CommentService commentService;
    public PostValidator postValidator;

    @Autowired
    public void setPostValidator(PostValidator postValidator) {
        this.postValidator = postValidator;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(ModelMap model, HttpServletRequest request) {
        WallPost wallPost = new WallPost();
        Comment comment = new Comment();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");
        List<WallPost> allPosts = postService.getPosts(user);
        if (allPosts != null) {
            model.addAttribute("posts", allPosts);
        } else {
            model.addAttribute("noPosts", "There is no new Post in this wall");
        }
        model.addAttribute("wallPost", wallPost);
        model.addAttribute("comment", comment);
        setModelAttributes(user, model);
        return "/homePage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewPost(WallPost wallPost, BindingResult result, HttpServletRequest request,
                             @RequestParam(value = "holler") MultipartFile file) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");
        wallPost.setAudioFileName(file.getOriginalFilename());
        wallPost.setAudioContentType(file.getContentType());
        wallPost.setPostBy(user);
        wallPost.setPostTo(user);
        wallPost.setDate(new Date());
        try {
            wallPost.setAudioContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        postValidator.validate(wallPost, result);
        if (!result.hasErrors()) {
            postService.saveNewPost(wallPost);
        }

        return "redirect:/posts";
    }

    @RequestMapping(value = "/playAudio/{id}")
    public void playAudio(HttpServletResponse response, @PathVariable(value = "id") int id) {
        log.debug("in audio streaming, id = {}", id);
        WallPost post = postService.getPost(id);
        OutputStream output = null;

        try {
            output = response.getOutputStream();
            response.setContentType(post.getAudioContentType());
            output.write(post.getAudioContent());
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

    private void setModelAttributes(User user, ModelMap model) {
        long numOfNewRequest = friendService.getNumberOfNewRequest(user);
        long numOfFriend = friendService.getNumberOfFriend(user);
        model.addAttribute("numOfNewRequest", numOfNewRequest);
        model.addAttribute("numOfFriends", numOfFriend);
        model.addAttribute("loggedUser", user);


    }

    @RequestMapping(value = "/saveFriendsPost/{id}", method = RequestMethod.POST)
    public String saveFriendsPost(WallPost wallPost, BindingResult result, HttpServletRequest request,
                                  @PathVariable(value = "id") int friendId,
                                  @RequestParam(value = "holler") MultipartFile file) {
        User postBy = (User) request.getSession().getAttribute("loggedUser");
        User postTo = userService.getUser(friendId);
        wallPost.setPostTo(postTo);
        wallPost.setPostBy(postBy);
        wallPost.setDate(new Date());
        wallPost.setAudioContentType(file.getContentType());
        wallPost.setAudioFileName(file.getOriginalFilename());
        try {
            wallPost.setAudioContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        postValidator.validate(wallPost, result);
        if (!result.hasErrors()) {
            log.debug("Saved the wall Post" + wallPost.getPostContent());
            postService.saveNewPost(wallPost);
        }

        if (request.getSession().getAttribute("friend") != null) {
            User friend = (User) request.getSession().getAttribute("friend");
            String id = Integer.toString(friend.getUserId());
            request.getSession().setAttribute("friend", null);
            return "redirect:/friends/showFullProfile/" + id;
        } else {
            return "redirect:/posts";
        }


    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable(value = "id") int postId) {
        WallPost post = postService.getPost(postId);
        List<Comment> commentListOnThisPost = commentService.getAllCommentsOnPost(post);
        commentService.deleteComment(commentListOnThisPost);
        postService.deletePost(post);
        return "redirect:/posts";
    }
}
