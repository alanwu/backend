package com.backend.core.events.posts;

import com.backend.core.events.CreatedEvent;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostCreatedEvent extends CreatedEvent {

    private final UUID newPostKey;
    private final PostDetails details;

    public PostCreatedEvent(final UUID newOrderKey, final PostDetails details) {
        this.newPostKey = newOrderKey;
        this.details = details;
    }

    public PostDetails getDetails() {
        return details;
    }

    public UUID getNewPostKey() {
        return newPostKey;
    }

}
