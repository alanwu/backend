package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

public class UpdateEvent {

    private BaseDomain updateObject;

    public UpdateEvent() {

    }

    public UpdateEvent(BaseDomain updateObject) {
        this.updateObject = updateObject;
    }

    public BaseDomain getUpdateObject() {
        return updateObject;
    }

    public void setUpdateObject(BaseDomain updateObject) {
        this.updateObject = updateObject;
    }

}
