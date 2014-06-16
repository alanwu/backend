package com.backend.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by alan on 2014-06-15.
 */
@Entity(name = "USER_ROLE")
public class UserRole extends BaseDomain {

    @Column(name = "ROLE")
    private String role;

    public UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
