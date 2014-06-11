package com.backend.rest.controller.fixture;

import com.backend.core.events.posts.PostCreatedEvent;
import com.backend.core.events.posts.PostDetailsEvent;

import java.util.UUID;

import static com.backend.rest.controller.fixture.RestDataFixture.customKeyPostDetails;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestEventFixtures {
    public static PostDetailsEvent postDetailsNotFound(UUID key) {
        return PostDetailsEvent.notFound(key);
    }

    public static PostDetailsEvent postDetailsEvent(UUID key) {
        return new PostDetailsEvent(key, customKeyPostDetails(key));
    }

    public static PostCreatedEvent postCreated(UUID key) {
        return new PostCreatedEvent(key, customKeyPostDetails(key));
    }

}
