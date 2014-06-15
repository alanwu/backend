package com.backend.core.events.posts;


import com.backend.core.domain.Post;
import com.backend.core.events.DeletedEvent;

public class PostDeletedEvent extends DeletedEvent {

    public PostDeletedEvent(long uid) {
        super(uid);
    }

    public PostDeletedEvent(long uid, Post post) {
        super(uid, post);
    }

    public static PostDeletedEvent deletionForbidden(long uid, Post details) {
        PostDeletedEvent ev = new PostDeletedEvent(uid, details);
        ev.entityFound = true;
        ev.setDeletionCompleted(false);
        return ev;
    }

    public static PostDeletedEvent notFound(long uid) {
        PostDeletedEvent ev = new PostDeletedEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
