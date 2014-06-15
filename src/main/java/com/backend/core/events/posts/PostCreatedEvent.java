package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.CreatedEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostCreatedEvent extends CreatedEvent {

    public PostCreatedEvent(final long newPostUid, final Post post) {
        super(newPostUid, post);
    }

}
