package net.therap.dao;

import net.therap.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/2/12
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public void saveNewUser(User user);

    public void updateInformation(User user);

    public void deleteUser(User user);

    public User getUser(String email);

    public User getUser(int userId);

    public List<User> getUserByName(String name);

    void updateUserInformation(User user);

    User getUserByEmail(String email);

}
