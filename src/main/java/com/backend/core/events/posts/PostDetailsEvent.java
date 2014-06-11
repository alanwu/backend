package com.backend.core.events.posts;

import com.backend.core.events.ReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetailsEvent extends ReadEvent {
    private long id;
    private PostDetails postDetails;

    private PostDetailsEvent(long key) {
        this.id = id;
    }

    public PostDetailsEvent(long id, PostDetails postDetails) {
        this.id = id;
        this.postDetails = postDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PostDetails getPostDetails() {
        return postDetails;
    }

    public static PostDetailsEvent notFound(long id) {
        PostDetailsEvent ev = new PostDetailsEvent(id);
        ev.entityFound = false;
        return ev;
    }

}
