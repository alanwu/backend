package com.backend.core.events.users;

import com.backend.core.events.ReadEvent;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class UserDetailsEvent extends ReadEvent {
    private UUID key;
    private UserDetails userDetails;

    private UserDetailsEvent(UUID key) {
        this.key = key;
    }

    public UserDetailsEvent(UUID key, UserDetails userDetails) {
        this.key = key;
        this.userDetails = userDetails;
    }

    public UUID getKey() {
        return key;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public static UserDetailsEvent notFound(UUID key) {
        UserDetailsEvent ev = new UserDetailsEvent(key);
        ev.entityFound = false;
        return ev;
    }

}
