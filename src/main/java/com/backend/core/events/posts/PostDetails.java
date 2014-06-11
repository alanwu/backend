package com.backend.core.events.posts;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetails {

    private UUID key;
    private String text;

    public PostDetails() {
        this.key = null;
    }

    public PostDetails(UUID key) {
        this.key = key;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
