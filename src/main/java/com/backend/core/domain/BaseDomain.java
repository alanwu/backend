package com.backend.core.domain;

import com.backend.rest.controller.PostQueriesController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by alan on 2014-06-14.
 */
public class BaseDomain extends ResourceSupport implements Serializable {
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            this.add(linkTo(PostQueriesController.class).slash(this.getUid()).withSelfRel());
        }
    }

    public boolean canBeDeleted() {
        return true;
    }

}
