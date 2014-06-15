package com.backend.core.events.users;


import com.backend.core.domain.User;
import com.backend.core.events.DeletedEvent;

public class UserDeletedEvent extends DeletedEvent {

    public UserDeletedEvent(long uid) {
        super(uid);
    }

    public UserDeletedEvent(long uid, User user) {
        super(uid, user);
    }

    public static UserDeletedEvent deletionForbidden(long uid, User details) {
        UserDeletedEvent ev = new UserDeletedEvent(uid, details);
        ev.entityFound = true;
        ev.setDeletionCompleted(false);
        return ev;
    }

    public static UserDeletedEvent notFound(long uid) {
        UserDeletedEvent ev = new UserDeletedEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
