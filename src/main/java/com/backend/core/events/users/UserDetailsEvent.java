package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.ReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class UserDetailsEvent extends ReadEvent {

    public UserDetailsEvent(long uid) {
        super(uid);
    }

    public UserDetailsEvent(long uid, User user) {
        super(uid, user);
    }

    public static UserDetailsEvent notFound(long uid) {
        UserDetailsEvent ev = new UserDetailsEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
