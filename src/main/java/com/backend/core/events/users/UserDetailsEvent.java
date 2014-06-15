package com.backend.core.events.users;

import com.backend.core.domain.User;
import com.backend.core.events.ReadEvent;
import com.backend.rest.controller.PostQueriesController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by alanw on 10/06/2014.
 */
public class UserDetailsEvent extends ReadEvent {

    public UserDetailsEvent(long uid) {
        super(uid);
    }

    public UserDetailsEvent(long uid, User user) {
        super(uid, user);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            user.add(linkTo(PostQueriesController.class).slash(user.getUid()).withSelfRel());
        }
    }

    public static UserDetailsEvent notFound(long uid) {
        UserDetailsEvent ev = new UserDetailsEvent(uid);
        ev.entityFound = false;
        return ev;
    }

}
