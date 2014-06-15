package com.backend.core.events;

import com.backend.core.domain.BaseDomain;

/**
 * Created by alan on 2014-06-10.
 */
public class DeletedEvent {
    private long uid;
    private BaseDomain objectToBeDeleted;
    private boolean deletionCompleted;
    protected boolean entityFound = true;

    public DeletedEvent() {

    }

    public DeletedEvent(long uid) {
        this.uid = uid;
    }

    public DeletedEvent(long uid, BaseDomain objectToBeDeleted) {
        this.uid = uid;
        this.objectToBeDeleted = objectToBeDeleted;
        this.deletionCompleted = true;
    }

    public long getUid() {
        return uid;
    }

    public BaseDomain getObjectToBeDeleted() {
        return objectToBeDeleted;
    }

    public void setObjectToBeDeleted(BaseDomain objectToBeDeleted) {
        this.objectToBeDeleted = objectToBeDeleted;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public void setDeletionCompleted(boolean deletionCompleted) {
        this.deletionCompleted = deletionCompleted;
    }

    public boolean isEntityFound() {
        return entityFound;
    }

}
