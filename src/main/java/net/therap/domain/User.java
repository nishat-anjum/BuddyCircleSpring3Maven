package net.therap.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 11/26/12
 * Time: 11:57 PM
 */
@Entity
public class User implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(User.class);
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String profession;
    private List<WallPost> wallPost = new ArrayList<WallPost>();
    private List<Friends> friendsList = new ArrayList<Friends>();
    private List<Comment> commentList = new ArrayList<Comment>();
    private byte[] image;
    private String imageFileName;
    private String phoneNo;
    private String aboutMe;
    private String confPassword;
    private String imageContentType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    @Transient
    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToMany(mappedBy = "friendOf")
    public List<Friends> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<Friends> friendsList) {
        this.friendsList = friendsList;
    }

    @Column(name = "FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "PROFESSION")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @OneToMany(mappedBy = "postBy")
    public List<WallPost> getWallPost() {
        return this.wallPost;
    }

    public void setWallPost(List<WallPost> wallPost) {
        this.wallPost = wallPost;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
//                ", joinDate='" + joinDate + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "commentBy")
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Column(name = "PHONE_NO")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "ABOUT_ME")
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Column(name = "IMAGE")
    @Lob
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "IMAGE_FILE_NAME")
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Column(name = "IMAGE_CONTENT_TYPE")
    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
}
