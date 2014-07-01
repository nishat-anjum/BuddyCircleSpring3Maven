package net.therap.services;

import net.therap.dao.FriendDao;
import net.therap.dao.UserDaoImpl;
import net.therap.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 11/28/12
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl  implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDaoImpl userDao;
    private FriendDao friendDao;

    @Autowired
    public void setFriendDao(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    @Override
    public void saveNewUser(User user) {
       log.debug("saveNewUser-e");
       userDao.saveNewUser(user);

    }

    @Override
    public void updateInformation(User user) {
        userDao.updateInformation(user);
    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public User getUser(String email) {
       return userDao.getUser(email);
    }
    @Override
    public User getUser(int userId) {
        return userDao.getUser(userId);
    }

    @Override
    public List<User> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name);
        return users;
    }

    @Override
    public void updateUserInformation(User user) {
        userDao.updateUserInformation(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getSearchResult(User user, List<User> listOfFriend, List<User> listOfUser) {
        List<User> searchResult = new ArrayList<User>();
        for(User user1 : listOfUser){
            if(user1.getEmail().equals(user.getEmail()))
                continue;
            if(friendDao.isBuddyOrNot(user,user1))
               searchResult.add(user1);
        }
        return searchResult;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
