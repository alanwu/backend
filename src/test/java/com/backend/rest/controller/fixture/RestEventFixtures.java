package com.backend.rest.controller.fixture;

import com.backend.core.events.posts.PostCreatedEvent;
import com.backend.core.events.posts.PostDetailsEvent;

import static com.backend.rest.controller.fixture.RestDataFixture.customKeyPostDetails;

/**
 * Created by alanw on 10/06/2014.
 */
public class RestEventFixtures {
    public static PostDetailsEvent postDetailsNotFound(long id) {
        return PostDetailsEvent.notFound(id);
    }

    public static PostDetailsEvent postDetailsEvent(long id) {
        return new PostDetailsEvent(id, customKeyPostDetails(id));
    }

    public static PostCreatedEvent postCreated(long id) {
        return new PostCreatedEvent(id, customKeyPostDetails(id));
    }

}
