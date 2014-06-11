package com.backend.core.events;

/**
 * Created by alan on 2014-06-10.
 */
public class DeletedEvent {
    protected boolean entityFound = true;

    public boolean isEntityFound() {
        return entityFound;
    }

}
