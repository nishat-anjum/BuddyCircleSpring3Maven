package net.therap.dao;

import net.therap.domain.Comment;
import net.therap.domain.WallPost;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/7/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentDao {
    void addNewComment(Comment comment);

    public List<Comment> getAllComments(WallPost post);

    public Comment getComment(int id);

    void deleteComment(Comment comment);

    void deleteCommentByPost(WallPost post);

    void deleteComment(List<Comment> commentListOnThisPost);

}
