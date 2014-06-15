package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.CreatedEvent;
import com.backend.rest.controller.PostQueriesController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostCreatedEvent extends CreatedEvent {

    public PostCreatedEvent(final long newPostUid, final Post post) {
        super(newPostUid, post);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            post.add(linkTo(PostQueriesController.class).slash(post.getUid()).withSelfRel());
        }
    }

}
