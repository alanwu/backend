package com.backend.rest.controller;

import com.backend.core.domain.User;
import com.backend.core.events.users.CreateUserEvent;
import com.backend.core.events.users.DeleteUserEvent;
import com.backend.core.events.users.UserCreatedEvent;
import com.backend.core.events.users.UserDeletedEvent;
import com.backend.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by alanw on 10/06/2014.
 */

@Controller
@RequestMapping("/users")
public class UserCommandsController {

    private static Logger LOG = Logger.getLogger(UserCommandsController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, UriComponentsBuilder builder) {
        UserCreatedEvent userCreatedEvent = userService.createUser(new CreateUserEvent(user));

        User newUser = (User) userCreatedEvent.getNewObject();

        HttpHeaders headers = new HttpHeaders();
        URI location = builder.path("/users/{uid}").buildAndExpand(String.valueOf(userCreatedEvent.getNewUid())).toUri();
        headers.setLocation(location);

        return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
    public ResponseEntity<User> cancelUser(@PathVariable String uid) {

        UserDeletedEvent userDeletedEvent = userService.deleteUser(new DeleteUserEvent(Long.parseLong(uid)));

        if (!userDeletedEvent.isEntityFound()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        User user = (User) userDeletedEvent.getObjectToBeDeleted();

        if (userDeletedEvent.isDeletionCompleted()) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
    }

}
