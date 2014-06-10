package com.backend.rest.controller;

import com.backend.core.events.users.RequestUserDetailsEvent;
import com.backend.core.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.UUID;

import static com.backend.rest.controller.fixture.RestEventFixtures.userDetailsEvent;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by alanw on 10/06/2014.
 */
public class ViewUserTest {

    MockMvc mockMvc;

    @InjectMocks
    UserQueriesController controller;

    @Mock
    UserService userService;

    UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatViewUserRendersCorrectly() throws Exception {

        when(userService.requestUserDetails(any(RequestUserDetailsEvent.class))).thenReturn(
                userDetailsEvent(key));

        this.mockMvc.perform(
                get("/users/{id}", key.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
                .andExpect(jsonPath("$.key").value(key.toString()));
    }

}
