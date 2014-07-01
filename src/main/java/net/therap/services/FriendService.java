package net.therap.services;

import net.therap.domain.Friends;
import net.therap.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/5/12
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FriendService {
    public void addNewFriend(Friends newFriend);
    public long getNumberOfFriend(User user);

    public List<User> getNewRequests(User user);

    void acceptFriendRequest(User user, User friend);

    long getNumberOfNewRequest(User user);

    List<User> getAllFriends(User user);

    void unFriend(User user, User friend);
}
