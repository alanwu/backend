package com.backend.rest.controller.fixture.user;

import com.backend.core.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestDataFixture {

    public static final String FIRST_NAME = "Alan";
    public static final String Last_NAME = "Wu";
    public static final String EMAIL = "alanwunan@gmail.com";
    public static final String PASSWORD = "123";

    public static final Long MY_ID = 1234567890L;

    public static User standardUser() {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(Last_NAME);
        user.setEmail(EMAIL);
        user.setClearTextPassword(PASSWORD);

        return user;
    }

    public static User customKeyUserDetails(long uid) {
        User user = new User();
        user.setUid(uid);
        user.setFirstName(FIRST_NAME);
        user.setLastName(Last_NAME);
        user.setEmail(EMAIL);
        user.setClearTextPassword(PASSWORD);

        return user;
    }

    public static User standardUserDetails() {
        Random random = new Random();

        return customKeyUserDetails(random.nextLong());
    }

    public static String standardUserJSON() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(standardUser());
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
