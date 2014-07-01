package net.therap.controller;

import net.therap.domain.Comment;
import net.therap.domain.User;
import net.therap.domain.WallPost;
import net.therap.services.CommentService;
import net.therap.services.PostService;
import net.therap.validator.CommentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/6/12
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private PostService postService;
    private CommentService commentService;
    private CommentValidator commentValidator;

    @Autowired
    public void setCommentValidator(CommentValidator commentValidator) {
        this.commentValidator = commentValidator;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/saveCommentContent/{id}", method = RequestMethod.POST)
    public String saveCommentContent(Comment comment, BindingResult result, HttpServletRequest request,
                                     @PathVariable(value = "id") int postId,
                                     @RequestParam(value = "holler", required = true) MultipartFile file) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        comment.setAudioFileName(file.getOriginalFilename());
        comment.setAudioContentType(file.getContentType());
        comment.setCommentBy(user);
        comment.setDate(new Date());
        WallPost post = postService.getPost(postId);
        comment.setPostId(post);
        commentValidator.validate(comment, result);
        try {
            comment.setAudio(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!result.hasErrors()) {
            commentService.addNewComment(comment);
        }

        if (request.getSession().getAttribute("friend") != null) {
            User friend = (User) request.getSession().getAttribute("friend");
            String id = Integer.toString(friend.getUserId());
            request.getSession().setAttribute("friend",null);
            return "redirect:/friends/showFullProfile/" + id;
        } else {
            return "redirect:/posts";
        }
    }

    @RequestMapping(value = "/playAudio/{id}")
    public void playAudio(HttpServletResponse response, @PathVariable(value = "id") int id) {
        log.debug("in audio streaming, id = {}", id);
        Comment comment = commentService.getComment(id);
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            response.setContentType(comment.getAudioContentType());
           // response.setContentType("audio/ogg");
            output.write(comment.getAudio());
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

    @RequestMapping(value = "/deleteComment/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable(value = "id") int commentId) {
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(comment);
        return "redirect:/posts";
    }

}
