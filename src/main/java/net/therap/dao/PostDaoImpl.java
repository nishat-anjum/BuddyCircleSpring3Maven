package net.therap.dao;

import net.therap.domain.User;
import net.therap.domain.WallPost;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/3/12
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostDaoImpl extends HibernateDaoSupport implements PostDao {
    @Override
    public void saveNewPost(WallPost wallPost) {
        getHibernateTemplate().save(wallPost);
    }

    @Override
    public List<WallPost> getPosts(User user) {
        String query = "FROM WallPost post WHERE (post.postBy = :user AND post.postTo = :user) OR post.postTo=:user ORDER BY post.date DESC ";
        List<WallPost> postList = this.getHibernateTemplate().findByNamedParam(query, "user", user);
        return postList;
    }

    @Override
    public WallPost getPosts(int id) {
        String query = "FROM WallPost post WHERE post.id = :id";
        List<WallPost> postList = this.getHibernateTemplate().findByNamedParam(query, "id", id);
        return postList.get(0);
    }

    public void deletePost(WallPost post) {
        getHibernateTemplate().delete(post);
    }
}
