package com.backend.core.domain;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alan on 2014-06-14.
 */
@MappedSuperclass
public class BaseDomain extends ResourceSupport implements Serializable {
    @Id
    @Column(name = "UID")
    @GeneratedValue(strategy=GenerationType.AUTO)
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
