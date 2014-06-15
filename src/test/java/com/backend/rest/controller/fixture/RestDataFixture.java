package com.backend.rest.controller.fixture;

import com.backend.core.domain.Post;

import java.util.Random;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestDataFixture {

    public static final String MY_POST = "I'm in testing mood";

    public static final Long MY_ID = 1234567890L;

    public static Post standardUser() {
        Post post = new Post();
        post.setText(MY_POST);

        return post;
    }

    public static Post customKeyPostDetails(long uid) {
        Post post = new Post();
        post.setUid(uid);
        post.setText(MY_POST);

        return post;
    }

    public static Post standardPostDetails() {
        Random random = new Random();

        return customKeyPostDetails(random.nextLong());
    }

    public static String standardPostJSON() {
        return "{\"text\": \"" + MY_POST + "\"}";
    }
}
