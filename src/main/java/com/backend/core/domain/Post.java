package com.backend.core.domain;

import com.backend.rest.controller.PostQueriesController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by alanw on 10/06/2014.
 */
public class Post extends ResourceSupport implements Serializable {

    private long uid;
    private String text;
    private String photoUrl;
    private String musicName;
    private String videoUrl;
    private User user;
    private List<Comment> comments;

    public Post() {

    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long uid) {
        this.uid = uid;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            this.add(linkTo(PostQueriesController.class).slash(this.uid).withSelfRel());
        }
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

    public boolean canBeDeleted() {
        return true;
    }

}
