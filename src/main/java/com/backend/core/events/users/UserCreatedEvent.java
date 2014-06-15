package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.CreatedEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public class UserCreatedEvent extends CreatedEvent {


    public UserCreatedEvent(final long newPostUid, final User user) {
        super(newPostUid, user);
    }

}
