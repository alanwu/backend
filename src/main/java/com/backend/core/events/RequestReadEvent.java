package com.backend.core.events;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestReadEvent {

    private long uid;

    public RequestReadEvent() {

    }

    public RequestReadEvent(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

}
