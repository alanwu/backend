package com.backend.core.events.posts;


import com.backend.core.domain.Post;
import com.backend.core.events.DeletedEvent;

public class PostDeletedEvent extends DeletedEvent {

    private long uid;
    private Post post;
    private boolean deletionCompleted;

    private PostDeletedEvent(long uid) {
        this.uid = uid;
    }

    public PostDeletedEvent(long uid, Post post) {
        this.uid = uid;
        this.post = post;
        this.deletionCompleted = true;
    }

    public long getUid() {
        return uid;
    }

    public Post getPost() {
        return post;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static PostDeletedEvent deletionForbidden(long uid, Post details) {
        PostDeletedEvent ev = new PostDeletedEvent(uid, details);
        ev.entityFound = true;
        ev.deletionCompleted = false;
        return ev;
    }

    public static PostDeletedEvent notFound(long uid) {
        PostDeletedEvent ev = new PostDeletedEvent(uid);
        ev.entityFound = false;
        return ev;
    }
}
