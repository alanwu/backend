package com.backend.core.domain;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

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
    }

    public boolean canBeDeleted() {
        return true;
    }

}
