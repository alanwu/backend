package com.backend.core.events.users;

import com.backend.core.events.RequestReadEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class RequestUserDetailsEvent extends RequestReadEvent {

    public RequestUserDetailsEvent(long uid) {
        super(uid);
    }

}
