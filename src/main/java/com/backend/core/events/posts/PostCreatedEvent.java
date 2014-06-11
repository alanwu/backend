package com.backend.core.events.posts;

import com.backend.core.events.CreatedEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostCreatedEvent extends CreatedEvent {

    private final long newPostId;
    private final PostDetails details;

    public PostCreatedEvent(final long newPostId, final PostDetails details) {
        this.newPostId = newPostId;
        this.details = details;
    }

    public PostDetails getDetails() {
        return details;
    }

    public long getNewPostId() {
        return newPostId;
    }

}
