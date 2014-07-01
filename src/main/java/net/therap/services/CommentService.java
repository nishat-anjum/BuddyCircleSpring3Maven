package net.therap.services;

import net.therap.domain.Comment;
import net.therap.domain.WallPost;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/7/12
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentService {
    public void addNewComment(Comment comment);

    List<Comment> getAllComments(WallPost post);

    Comment getComment(int id);

    void deleteComment(Comment comment);

    void deleteCommentsByPost(WallPost post);
    public List<Comment> getAllCommentsOnPost(WallPost post);

    void deleteComment(List<Comment> commentListOnThisPost);

}
