package net.therap.services;

import net.therap.dao.PostDaoImpl;
import net.therap.domain.User;
import net.therap.domain.WallPost;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/3/12
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostServiceImpl implements PostService {


    private PostDaoImpl postDao;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void saveNewPost(WallPost wallPost) {
        postDao.saveNewPost(wallPost);
    }

    @Override
    public List<WallPost> getPosts(User user) {
        List<WallPost> wallPosts = postDao.getPosts(user);
        for(WallPost post : wallPosts){
            post.setCommentList(commentService.getAllCommentsOnPost(post));
        }
        return wallPosts;
    }

    @Override
    public WallPost getPost(int id) {
        return postDao.getPosts(id);
    }

    @Override
    public void deletePost(WallPost post) {
        postDao.deletePost(post);
    }

    public void setPostDao(PostDaoImpl postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
