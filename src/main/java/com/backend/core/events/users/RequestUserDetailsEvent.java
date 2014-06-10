package com.backend.core.events.users;

import com.backend.core.events.RequestReadEvent;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestUserDetailsEvent extends RequestReadEvent {
    private UUID key;

    public RequestUserDetailsEvent(UUID key) {
        this.key = key;
    }

    public UUID getKey() {
        return key;
    }

}
