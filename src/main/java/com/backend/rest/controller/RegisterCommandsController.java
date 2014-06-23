package com.backend.rest.controller;

import com.backend.core.domain.User;
import com.backend.core.events.users.CreateUserEvent;
import com.backend.core.events.users.UserCreatedEvent;
import com.backend.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/register")
public class RegisterCommandsController {

    private static Logger LOG = Logger.getLogger(RegisterCommandsController.class);

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

}
