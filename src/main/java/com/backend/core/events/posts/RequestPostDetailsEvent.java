package com.backend.core.events.posts;

import com.backend.core.events.RequestReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestPostDetailsEvent extends RequestReadEvent {
    private long id;

    public RequestPostDetailsEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
