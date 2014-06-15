package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.UpdatedEvent;

public class UserUpdatedEvent extends UpdatedEvent {

    public UserUpdatedEvent(long uid, User user) {
        super(uid, user);
    }

}
