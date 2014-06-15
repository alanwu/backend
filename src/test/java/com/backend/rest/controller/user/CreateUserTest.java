package com.backend.rest.controller.user;

import com.backend.core.events.users.CreateUserEvent;
import com.backend.core.service.UserService;
import com.backend.rest.controller.UserCommandsController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.backend.rest.controller.fixture.user.RestDataFixture.*;
import static com.backend.rest.controller.fixture.user.RestEventFixtures.userCreated;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alanw on 10/06/2014.
 */
public class CreateUserTest {

    MockMvc mockMvc;

    @InjectMocks
    UserCommandsController controller;

    @Mock
    UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller) .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

        when(userService.createUser(any(CreateUserEvent.class))).thenReturn(
                userCreated(MY_ID.longValue()));

    }

    @Test
    public void thatCreateUserUsesHttpCreated() throws Exception {

        this.mockMvc.perform(
                post("/users")
                        .content(standardUserJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void thatCreateUserRendersAsJson() throws Exception {

        this.mockMvc.perform(
                post("/users")
                        .content(standardUserJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.uid").value(MY_ID.intValue()));
    }

    @Test
    public void thatCreateUserPassesLocationHeader() throws Exception {

        this.mockMvc.perform(
                post("/users")
                        .content(standardUserJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", Matchers.endsWith("/users/" + MY_ID.toString())));
    }

}
