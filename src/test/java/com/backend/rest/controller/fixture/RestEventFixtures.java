package com.backend.rest.controller.fixture;

import com.backend.core.events.users.UserDetailsEvent;

import java.util.UUID;

import static com.backend.rest.controller.fixture.RestDataFixture.customKeyUserDetails;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestEventFixtures {
    public static UserDetailsEvent userDetailsNotFound(UUID key) {
        return UserDetailsEvent.notFound(key);
    }
    public static UserDetailsEvent userDetailsEvent(UUID key) {
        return new UserDetailsEvent(key, customKeyUserDetails(key));
    }

}
