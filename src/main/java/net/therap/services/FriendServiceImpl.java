package net.therap.services;

import net.therap.dao.FriendDaoImpl;
import net.therap.domain.Friends;
import net.therap.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/5/12
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class FriendServiceImpl implements FriendService {
    private FriendDaoImpl friendDao;
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setFriendDao(FriendDaoImpl friendDao) {
        this.friendDao = friendDao;
    }

    @Override
    public void addNewFriend(Friends newFriend) {
        friendDao.addNewFriend(newFriend);
    }

    @Override
    public long getNumberOfFriend(User user) {
        log.debug("FriendImplService Id " + user.getUserId());
        return friendDao.getNumberOfFriend(user);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getNewRequests(User user) {
        List<Friends> newRequests = friendDao.getNewRequests(user);
        List<User> newFriendRequest = new ArrayList<User>();
        if(newRequests.size()!=0){
            for(Friends friends : newRequests){
                newFriendRequest.add(friends.getFriendOf());
                log.debug("the User "+friends.getFriendOf().getFirstName());
            }
        }
        return newFriendRequest;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void acceptFriendRequest(User user, User friend) {
        Friends friendShip = friendDao.getFriendShip(user,friend);
        log.debug("Here is the FriendShip "+ friendShip.getAcceptance());
        friendShip.setAcceptance(true);
        friendDao.acceptFriendRequest(friendShip);

    }

    @Override
    public long getNumberOfNewRequest(User user) {
        return friendDao.getNumberOfNewRequest(user);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getAllFriends(User user) {
        List<Friends> allFriends = friendDao.getAllFriends(user);
        log.debug("friend list from " + allFriends.size()+ " logged User "+user.getUserId());
        List<User> friendList = new ArrayList<User>();
        if(allFriends.size()!=0){
        for(Friends friends : allFriends){
            log.debug("into Loop  " + friends.getFriendOf());
            log.debug("into Loop friend " + friends.getFriend());
            if(friends.getFriend().getUserId()==user.getUserId()){
                log.debug("user "+ friends.getFriend());
                friendList.add(friends.getFriendOf());
            }
            if(friends.getFriendOf().getUserId()==user.getUserId()){
                friendList.add(friends.getFriend());
                log.debug("frnd Of "+friends.getFriendOf());
            }
        }
        }
        return friendList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void unFriend(User user, User friend) {
        friendDao.unFriend(user,friend);
    }


}
