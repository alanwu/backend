package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.ReadEvent;
import com.backend.rest.controller.PostQueriesController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by alanw on 10/06/2014.
 */
public class PostDetailsEvent extends ReadEvent {

    public PostDetailsEvent(long uid) {
        super(uid);
    }

    public PostDetailsEvent(long uid, Post post) {
       super(uid, post);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            post.add(linkTo(PostQueriesController.class).slash(post.getUid()).withSelfRel());
        }
    }

    public static PostDetailsEvent notFound(long uid) {
        PostDetailsEvent ev = new PostDetailsEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
