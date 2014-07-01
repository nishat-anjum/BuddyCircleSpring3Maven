package net.therap.dao;

import net.therap.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/2/12
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void saveNewUser(User user) {

        log.debug("Registerred user " + user.toString());
        getHibernateTemplate().save(user);
    }

    @Override
    public void updateInformation(User user) {
        getHibernateTemplate().update(user);
    }

    @Override
    public void deleteUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User getUser(String email) {
        String query = "FROM User user WHERE user.email = :email";
        List userList = getHibernateTemplate().findByNamedParam(query, "email", email);
        return (userList.size() == 0) ? null : (User) userList.get(0);
    }

    @Override
    public User getUser(int userId) {

        String query = "FROM User user WHERE user.userId = :userId";
        List userList = getHibernateTemplate().findByNamedParam(query, "userId", userId);
        return (userList.size() == 0) ? null : (User) userList.get(0);

        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getUserByName(String name) {
        String query = "FROM User user WHERE user.firstName like ? OR user.lastName = ?";
        List<User> userList = getHibernateTemplate().find(query, '%' + name + '%', name);
        return userList;

    }

    @Override
    public void updateUserInformation(User user) {
        getHibernateTemplate().update(user);
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "FROM User user WHERE user.email = :email";
        List<User> userList = getHibernateTemplate().findByNamedParam(query, "email", email);
        return (userList.size() == 0) ? null : userList.get(0);
    }

}