package net.therap.dao;

import net.therap.domain.Friends;
import net.therap.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/4/12
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class FriendDaoImpl extends HibernateDaoSupport implements FriendDao {
    private static final Logger log = LoggerFactory.getLogger(FriendDaoImpl.class);

    public void addNewFriend(Friends newFriend) {
        getHibernateTemplate().save(newFriend);
    }

    @Override
    public long getNumberOfFriend(User user) {
        boolean accept = true;
        String query = "SELECT COUNT(*) FROM Friends friends " +
                "WHERE friends.acceptance = :accept AND (friends.friendOf = :user OR friends.friend = :user)";
        return (Long) getHibernateTemplate().findByNamedParam(query,
                new String[]{"accept", "user", "user"}, new Object[]{accept, user, user}).get(0);
    }


    @Override
    public List<Friends> getNewRequests(User user) {
        boolean accept = false;
        log.debug("The user who wants Request List " + user.getFirstName() + " " + user.getLastName());
        String query = "FROM Friends friends WHERE friends.friend = :user AND friends.acceptance = :accept";
        List<Friends> newRequest = getHibernateTemplate().findByNamedParam(query, new String[]{"user", "accept"}, new Object[]{user, accept});
        return newRequest;

    }

    @Override
    public void acceptFriendRequest(Friends friends) {

        log.debug("Accepted Friend Request " + friends.getFriendOf().getFirstName() + friends.getFriend().getFirstName());
        getHibernateTemplate().update(friends);


    }

    @Override
    public Friends getFriendShip(User user, User friend) {
        String query = "FROM Friends friends WHERE friends.friendOf = :friend AND friends.friend = :user";
        List<Friends> FriendList = getHibernateTemplate().findByNamedParam(query, new String[]{"friend", "user"}, new Object[]{friend, user});
        return (FriendList.size() == 0) ? null : FriendList.get(0);
    }

    @Override
    public boolean isBuddyOrNot(User user, User friend) {
        String query = "FROM Friends friends WHERE (friends.friendOf = :friend AND friends.friend = :user) OR (friends.friendOf = :user AND friends.friend = :friend)";
        List<Friends> FriendList = getHibernateTemplate().findByNamedParam(query, new String[]{"friend", "user"}, new Object[]{friend, user});
        return (FriendList.size() == 0) ? true : false;
    }

    @Override
    public long getNumberOfNewRequest(User user) {
        boolean accept = false;
        String query = "SELECT COUNT(*) FROM Friends friends " +
                "WHERE friends.acceptance = :accept AND friends.friend = :user";
        return (Long) getHibernateTemplate().findByNamedParam(query,
                new String[]{"accept", "user", "user"}, new Object[]{accept, user, user}).get(0);

    }

    @Override
    public List<Friends> getAllFriends(User user) {
        boolean accept = true;
        String query = "FROM Friends friends " +
                "WHERE friends.acceptance = :accept AND (friends.friendOf = :user OR friends.friend = :user)";
        List<Friends> allFriends = getHibernateTemplate().findByNamedParam(query, new String[]{"accept", "user", "user"}, new Object[]{accept, user, user});
        log.debug("no of friend " + allFriends.size());
        return allFriends;
    }

    @Override
    public void unFriend(User user, User friend) {
        Friends friends = getFriendShipforDelete(user, friend);
        getHibernateTemplate().delete(friends);
    }

    private Friends getFriendShipforDelete(User user, User friend) {
        String query = "FROM Friends friends WHERE (friends.friendOf = :friend AND friends.friend = :user) OR (friends.friendOf = :user AND friends.friend = :friend)";
        List<Friends> FriendList = getHibernateTemplate().findByNamedParam(query, new String[]{"friend", "user"}, new Object[]{friend, user});
        return (FriendList.size() == 0) ? null : FriendList.get(0);
    }
}
