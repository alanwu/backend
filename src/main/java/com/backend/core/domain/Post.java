package com.backend.core.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alanw on 10/06/2014.
 */
@Entity(name = "POST")
public class Post extends BaseDomain {

    @Column(name = "TEXT")
    private String text;

    @Column(name = "PHOTO_URL")
    private String photoUrl;

    @Column(name = "MUSIC_NAME")
    private String musicName;

    @Column(name = "VIDEO_URL")
    private String videoUrl;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @Transient
    private List<Comment> comments;

    public Post() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
