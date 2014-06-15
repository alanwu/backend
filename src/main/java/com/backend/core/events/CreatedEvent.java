package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

/**
 * Created by alanw on 10/06/2014.
 */
public class CreatedEvent {

    private long newUid;
    private BaseDomain newObject;

    public CreatedEvent() {

    }

    public CreatedEvent(long newUid, BaseDomain newObject) {
        this.newUid = newUid;
        this.newObject = newObject;
    }

    public long getNewUid() {
        return this.newUid;
    }

    public BaseDomain getNewObject() {
        return newObject;
    }

    public void setNewObject(BaseDomain newObject) {
        this.newObject = newObject;
    }

}
