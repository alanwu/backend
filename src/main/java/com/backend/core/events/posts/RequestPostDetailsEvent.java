package com.backend.core.events.posts;

import com.backend.core.events.RequestReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestPostDetailsEvent extends RequestReadEvent {
    private long uid;

    public RequestPostDetailsEvent(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

}
