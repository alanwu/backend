package com.backend.core.events.posts;

import com.backend.core.events.ReadEvent;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetailsEvent extends ReadEvent {
    private UUID key;
    private PostDetails postDetails;

    private PostDetailsEvent(UUID key) {
        this.key = key;
    }

    public PostDetailsEvent(UUID key, PostDetails postDetails) {
        this.key = key;
        this.postDetails = postDetails;
    }

    public UUID getKey() {
        return key;
    }

    public PostDetails getPostDetails() {
        return postDetails;
    }

    public static PostDetailsEvent notFound(UUID key) {
        PostDetailsEvent ev = new PostDetailsEvent(key);
        ev.entityFound = false;
        return ev;
    }

}
