package com.backend.core.events.posts;

import com.backend.core.events.RequestReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestPostDetailsEvent extends RequestReadEvent {

    public RequestPostDetailsEvent(long uid) {
        super(uid);
    }

}
