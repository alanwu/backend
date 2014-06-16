package com.backend.rest.controller;

import com.backend.core.domain.User;
import com.backend.core.events.users.RequestUserDetailsEvent;
import com.backend.core.events.users.UserDetailsEvent;
import com.backend.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alanw on 10/06/2014.
 */

@Controller
@RequestMapping("/users")
public class UserQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(UserQueriesController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> viewUser(@PathVariable String id) {

        UserDetailsEvent details = userService.getUserDetails(new RequestUserDetailsEvent(Long.parseLong(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        User user = (User) details.getDetails();

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
}
