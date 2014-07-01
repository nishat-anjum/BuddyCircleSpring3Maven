package net.therap.dao;

import net.therap.domain.Friends;
import net.therap.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/4/12
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FriendDao {
    public void addNewFriend(Friends newFriend);

    public long getNumberOfFriend(User user);

    public List<Friends> getNewRequests(User user);

    public void acceptFriendRequest(Friends friends);

    public Friends getFriendShip(User user, User friend);

    long getNumberOfNewRequest(User user);

    List<Friends> getAllFriends(User user);

    void unFriend(User user, User friend);

    public boolean isBuddyOrNot(User user, User friend);
}
