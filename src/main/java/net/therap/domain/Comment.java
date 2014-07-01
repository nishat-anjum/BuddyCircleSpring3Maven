package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/6/12
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Comment implements Serializable {
    private int commentId;
    private String content;
    private Date date;
    private User commentBy;
    private byte[] image;
    private byte[] audio;
    private String imageFileName;
    private String audioFileName;
    private WallPost postId;
    private String audioContentType;


    @ManyToOne
    @JoinColumn(name = "POST_ID")
    public WallPost getPostId() {
        return postId;
    }

    public void setPostId(WallPost postId) {
        this.postId = postId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Column(name = "DETAILS")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "COMMENT_BY")
    public User getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(User commentBy) {
        this.commentBy = commentBy;
    }

    @Column(name = "IMAGE")
    @Lob
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "AUDIO")
    @Lob
    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    @Column(name = "IMAGE_FILE_NAME")
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Column(name = "AUDIO_FILE_NAME")
    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    @Column(name = "AUDIO_CONTENT_TYPE")
    public String getAudioContentType() {
        return audioContentType;
    }

    public void setAudioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
    }

}
