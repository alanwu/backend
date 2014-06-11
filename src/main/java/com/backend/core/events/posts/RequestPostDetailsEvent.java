package com.backend.core.events.posts;

import com.backend.core.events.RequestReadEvent;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestPostDetailsEvent extends RequestReadEvent {
    private UUID key;

    public RequestPostDetailsEvent(UUID key) {
        this.key = key;
    }

    public UUID getKey() {
        return key;
    }

}
