package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.UpdatedEvent;

public class PostUpdatedEvent extends UpdatedEvent {

    public PostUpdatedEvent(long uid, Post post) {
        super(uid, post);
    }

}
