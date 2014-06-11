package com.backend.core.events.posts;

import com.backend.core.events.CreateEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class CreatePostEvent extends CreateEvent {
    private PostDetails details;

    public CreatePostEvent(PostDetails details) {
        this.details = details;
    }

    public PostDetails getDetails() {
        return details;
    }

}
