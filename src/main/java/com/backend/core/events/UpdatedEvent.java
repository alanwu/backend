package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

public class UpdatedEvent {

    private long uid;
    private BaseDomain details;

    public UpdatedEvent() {

    }

    public UpdatedEvent(long uid, BaseDomain details) {
        this.uid = uid;
        this.details = details;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public BaseDomain getDetails() {
        return details;
    }

    public void setDetails(BaseDomain details) {
        this.details = details;
    }

}
