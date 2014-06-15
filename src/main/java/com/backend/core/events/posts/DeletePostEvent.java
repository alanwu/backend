package com.backend.core.events.posts;

import com.backend.core.events.DeleteEvent;

public class DeletePostEvent extends DeleteEvent {

    public DeletePostEvent(final long uid) {
        super(uid);
    }

}
