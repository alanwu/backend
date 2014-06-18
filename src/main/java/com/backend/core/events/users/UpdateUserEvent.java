package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.UpdateEvent;

/**
 * Created by alan on 2014-06-14.
 */
public class UpdateUserEvent extends UpdateEvent {

    public UpdateUserEvent(User user) {
        super(user);
    }

}
