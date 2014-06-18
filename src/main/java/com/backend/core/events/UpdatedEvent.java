package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

public class UpdatedEvent {

    private BaseDomain updatedObject;

    public UpdatedEvent() {

    }

    public UpdatedEvent(BaseDomain updatedObject) {
        this.updatedObject = updatedObject;
    }

    public BaseDomain getUpdatedObject() {
        return updatedObject;
    }

    public void setUpdatedObject(BaseDomain updatedObject) {
        this.updatedObject = updatedObject;
    }

}
