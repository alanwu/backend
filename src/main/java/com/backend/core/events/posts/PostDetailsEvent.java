package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.ReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetailsEvent extends ReadEvent {
    private long uid;
    private Post post;

    private PostDetailsEvent(long key) {
        this.uid = uid;
    }

    public PostDetailsEvent(long uid, Post post) {
        this.uid = uid;
        this.post = post;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Post getPost() {
        return post;
    }

    public static PostDetailsEvent notFound(long uid) {
        PostDetailsEvent ev = new PostDetailsEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
