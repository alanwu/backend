package com.backend.core.events;

public class DeleteEvent {

    private long uid;

    public DeleteEvent() {

    }

    public DeleteEvent(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return this.uid;
    }

}
