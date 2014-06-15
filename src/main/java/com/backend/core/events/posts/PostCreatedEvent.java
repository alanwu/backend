package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.CreatedEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostCreatedEvent extends CreatedEvent {

    private final long newPostUid;
    private final Post post;

    public PostCreatedEvent(final long newPostUid, final Post post) {
        this.newPostUid = newPostUid;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public long getNewPostUid() {
        return newPostUid;
    }

}
