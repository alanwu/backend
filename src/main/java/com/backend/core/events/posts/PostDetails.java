package com.backend.core.events.posts;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetails {

    private long id;
    private String text;

    public PostDetails() {

    }

    public PostDetails(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
