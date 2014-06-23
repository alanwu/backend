package com.backend.rest.controller.fixture.user;

import com.backend.core.events.users.UserCreatedEvent;
import com.backend.core.events.users.UserDeletedEvent;
import com.backend.core.events.users.UserDetailsEvent;
import com.backend.core.events.users.UserUpdatedEvent;

import static com.backend.rest.controller.fixture.user.RestDataFixture.*;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestEventFixtures {
    public static UserDetailsEvent userDetailsNotFound(long id) {
        return (UserDetailsEvent) UserDetailsEvent.notFound(id);
    }

    public static UserDetailsEvent userDetailsEvent(long id) {
        return new UserDetailsEvent(id, customKeyUserDetails(id));
    }

    public static UserCreatedEvent userCreated(long id) {
        return new UserCreatedEvent(id, customKeyUserDetails(id));
    }

    public static UserUpdatedEvent userUpdated(long id) {
        return new UserUpdatedEvent(customKeyUserDetails(id));
    }

    public static UserDeletedEvent userDeleted(long id) {
        return new UserDeletedEvent(id, standardUserDetails());
    }

    public static UserDeletedEvent userDeletedFailed(long id) {
        return (UserDeletedEvent) UserDeletedEvent.deletionForbidden(id, standardUserDetails());
    }

    public static UserDeletedEvent userDeletedNotFound(long id) {
        return (UserDeletedEvent) UserDeletedEvent.notFound(id);
    }

}
