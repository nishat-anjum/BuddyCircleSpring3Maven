package net.therap.services;

import net.therap.dao.CommentDao;
import net.therap.domain.Comment;
import net.therap.domain.WallPost;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/7/12
 * Time: 10:52 AM
 * To change this template use File | Settings | File Templates.
 */

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    @Override
    public void addNewComment(Comment comment) {
        commentDao.addNewComment(comment);

    }

    @Override
    public List<Comment> getAllComments(WallPost post) {
        List<Comment> allComment = commentDao.getAllComments(post);
        return allComment;
    }

    @Override
    public Comment getComment(int id) {
        return commentDao.getComment(id);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDao.deleteComment(comment);
    }

    @Override
    public void deleteCommentsByPost(WallPost post) {
        commentDao.deleteCommentByPost(post);
        //To change body of implemented methods use File | Settings | File Templates.
    }
    public List<Comment> getAllCommentsOnPost(WallPost post){
        return commentDao.getAllComments(post);
    }

    @Override
    public void deleteComment(List<Comment> commentListOnThisPost) {

        commentDao.deleteComment(commentListOnThisPost);
    }
}
