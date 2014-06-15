package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

/**
 * Created by alanw on 10/06/2014.
 */
public class CreateEvent {
    private BaseDomain newObject;

    public CreateEvent() {

    }

    public CreateEvent(BaseDomain newObject) {
        this.newObject = newObject;
    }

    public BaseDomain getNewObject() {
        return newObject;
    }

    public void setNewObject(BaseDomain newObject) {
        this.newObject = newObject;
    }

}
