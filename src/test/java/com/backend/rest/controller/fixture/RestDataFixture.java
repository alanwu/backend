package com.backend.rest.controller.fixture;

import com.backend.core.domain.User;
import com.backend.core.events.users.UserDetails;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestDataFixture {

    public static User standardUser() {
        User user = new User("alan", "wu");

        return user;
    }

    public static UserDetails customKeyUserDetails(UUID key) {
        UserDetails userdetails = new UserDetails(key);

        return userdetails;
    }
    public static UserDetails standardUserDetails() {
        return customKeyUserDetails(UUID.randomUUID());
    }

}
