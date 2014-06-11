package com.backend.core.domain;

import com.backend.core.events.posts.PostDetails;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class Post {

    private final UUID key;
    private String text;
    private String photoUrl;
    private String musicName;
    private String videoUrl;
    private User user;
    private List<Comment> comments;

    public Post() {
        this.key = UUID.randomUUID();
    }

    public UUID getKey() {
        return this.key;
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

    public PostDetails toPostDetails() {
        PostDetails details = new PostDetails();

        BeanUtils.copyProperties(this, details);

        return details;
    }

    public static Post fromPostDetails(PostDetails postDetails) {
        Post post = new Post();

        BeanUtils.copyProperties(postDetails, post);

        return post;
    }
}
