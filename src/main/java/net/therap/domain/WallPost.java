package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anjum
 * Date: 12/3/12
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class WallPost implements Serializable {
    private int postId;
    private String postContent;
    private Date date;
    private User postBy;
    private User postTo;
    private String audioFileName;
    private String image;
    private byte[] audioContent;
    private String audioContentType;
    List<Comment> commentList = new ArrayList<Comment>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    public int getPostId() {
        return postId;
    }

    @Column(name = "AUDIO_CONTENT")
    @Lob
    public byte[] getAudioContent() {
        return audioContent;
    }

    public void setAudioContent(byte[] audioContent) {
        this.audioContent = audioContent;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Column(name = "DETAILS")
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "POST_BY")
    public User getPostBy() {
        return postBy;
    }

    public void setPostBy(User postBy) {
        this.postBy = postBy;
    }

    @ManyToOne
    @JoinColumn(name = "POST_TO")
    public User getPostTo() {
        return postTo;
    }

    public void setPostTo(User postTo) {
        this.postTo = postTo;
    }

    @Column(name = "AUDIO_FILE_NAME")
    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    @Column(name = "IMAGE")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(mappedBy = "postId")
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Column(name = "AUDIO_CONTENT_TYPE")
    public String getAudioContentType() {
        return audioContentType;
    }

    public void setAudioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
    }
}
