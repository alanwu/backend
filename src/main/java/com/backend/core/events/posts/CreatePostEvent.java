package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.CreateEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class CreatePostEvent extends CreateEvent {

    public CreatePostEvent(Post post) {
        super(post);
    }

}
