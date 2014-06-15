package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.CreateEvent;

/**
 * Created by alan on 2014-06-14.
 */
public class CreateUserEvent extends CreateEvent {

    public CreateUserEvent(User user) {
        super(user);
    }

}
