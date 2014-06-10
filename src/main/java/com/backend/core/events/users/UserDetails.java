package com.backend.core.events.users;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class UserDetails {

    private UUID key;

    public UserDetails() {
        this.key = null;
    }

    public UserDetails(UUID key) {
        this.key = key;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

}
