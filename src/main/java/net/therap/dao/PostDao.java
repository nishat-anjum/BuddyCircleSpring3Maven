package net.therap.dao;

import net.therap.domain.User;
import net.therap.domain.WallPost;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/3/12
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PostDao {
    public void saveNewPost(WallPost wallPost);

    public List<WallPost> getPosts(User user);

    WallPost getPosts(int id);
}
