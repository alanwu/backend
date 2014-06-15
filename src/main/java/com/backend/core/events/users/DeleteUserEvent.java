package com.backend.core.events.users;

import com.backend.core.events.DeleteEvent;

public class DeleteUserEvent extends DeleteEvent {

    public DeleteUserEvent(final long uid) {
        super(uid);
    }

}
