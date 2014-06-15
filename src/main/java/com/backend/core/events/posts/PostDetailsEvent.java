package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.ReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetailsEvent extends ReadEvent {

    public PostDetailsEvent(long uid) {
        super(uid);
    }

    public PostDetailsEvent(long uid, Post post) {
       super(uid, post);
    }

    public static PostDetailsEvent notFound(long uid) {
        PostDetailsEvent ev = new PostDetailsEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
