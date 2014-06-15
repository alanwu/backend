package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.CreatedEvent;
import com.backend.rest.controller.PostQueriesController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by alanw on 10/06/2014.
 */
public class UserCreatedEvent extends CreatedEvent {


    public UserCreatedEvent(final long newPostUid, final User user) {
        super(newPostUid, user);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            user.add(linkTo(PostQueriesController.class).slash(user.getUid()).withSelfRel());
        }
    }

}
