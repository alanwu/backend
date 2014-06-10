package com.backend.core.domain;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by alanw on 10/06/2014.
 */
public class User {
    private final UUID key;
    private String firstName;
    private String lastName;

    public User() {
        this.key = UUID.randomUUID();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
