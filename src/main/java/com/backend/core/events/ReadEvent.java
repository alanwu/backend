package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

/**
 * Created by alanw on 10/06/2014.
 */
public class ReadEvent {

    private long uid;
    private BaseDomain details;
    protected boolean entityFound = true;

    public ReadEvent() {

    }

    public ReadEvent(long uid) {
        this.uid = uid;
    }

    public ReadEvent(long uid, BaseDomain details) {
        this.uid = uid;
        this.details = details;
    }

    public long getUid() {
        return uid;
    }

    public BaseDomain getDetails() {
        return details;
    }

    public void setDetails(BaseDomain details) {
        this.details = details;
    }

    public boolean isEntityFound() {
        return entityFound;
    }

}
