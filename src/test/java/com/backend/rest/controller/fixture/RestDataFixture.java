package com.backend.rest.controller.fixture;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.PostDetails;

import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestDataFixture {

    public static final String MY_POST = "I'm in testing mood";

    public static Post standardUser() {
        Post post = new Post();
        post.setText(MY_POST);

        return post;
    }

    public static PostDetails customKeyPostDetails(UUID key) {
        PostDetails postDetails = new PostDetails(key);
        postDetails.setText(MY_POST);

        return postDetails;
    }

    public static PostDetails standardPostDetails() {
        return customKeyPostDetails(UUID.randomUUID());
    }

    public static String standardPostJSON() {
        return "{\"text\": \"I'm in testing mood\"}";
    }
}
