package com.backend.core.service;

import com.backend.core.events.users.UserDetailsEvent;
import com.backend.core.events.users.RequestUserDetailsEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public interface  UserService {

    public UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent);

}
