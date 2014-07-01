package net.therap.dao;

import net.therap.domain.Comment;
import net.therap.domain.WallPost;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/7/12
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {
    @Override
    public void addNewComment(Comment comment) {
        getHibernateTemplate().save(comment);

    }

    @Override
    public List<Comment> getAllComments(WallPost post) {
        String query = "FROM Comment comments WHERE comments.postId = :post ORDER BY comments.date ASC";
        List<Comment> commentList = this.getHibernateTemplate().findByNamedParam(query, "post", post);
        return commentList;
    }

    @Override
    public Comment getComment(int id) {
        String query = "FROM Comment comment WHERE comment.id = :id";
        List<Comment> comment = this.getHibernateTemplate().findByNamedParam(query, "id", id);
        return comment.get(0);
    }

    @Override
    public void deleteComment(Comment comment) {
        getHibernateTemplate().delete(comment);
    }

    @Override
    public void deleteCommentByPost(WallPost post) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        String query = "DELETE Comment AS comment WHERE comment.postId = :post";
        Query query1 = session.createQuery(query);
        query1.setParameter("post", post);
        query1.executeUpdate();
    }

    @Override
    public void deleteComment(List<Comment> commentListOnThisPost) {
        getHibernateTemplate().deleteAll(commentListOnThisPost);
    }
}
