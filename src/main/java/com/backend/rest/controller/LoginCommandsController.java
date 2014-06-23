package com.backend.rest.controller;

import com.backend.core.service.UserService;
import com.backend.rest.domain.LoginRequest;
import com.backend.rest.domain.LoginResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by alanw on 10/06/2014.
 */

@Controller
@RequestMapping("/login")
public class LoginCommandsController {

    private static Logger LOG = Logger.getLogger(LoginCommandsController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {

        return new ResponseEntity<LoginResponse>(HttpStatus.OK);
    }

}
