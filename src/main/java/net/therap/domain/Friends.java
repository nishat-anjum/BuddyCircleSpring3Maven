package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/4/12
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Friends implements Serializable {
    private int id;
    private User friendOf;
    private User friend;
    private boolean acceptance;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(User friendOf) {
        this.friendOf = friendOf;
    }

    @ManyToOne
    @JoinColumn(name = "FRIEND_ID")
    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Column(name = "ACCEPTANCE")
    public boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }
}
