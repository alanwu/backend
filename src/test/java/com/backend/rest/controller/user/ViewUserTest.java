package com.backend.rest.controller.user;

import com.backend.core.events.users.RequestUserDetailsEvent;
import com.backend.core.service.UserService;
import com.backend.rest.controller.UserQueriesController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.backend.rest.controller.fixture.user.RestDataFixture.FIRST_NAME;
import static com.backend.rest.controller.fixture.user.RestDataFixture.MY_ID;
import static com.backend.rest.controller.fixture.user.RestEventFixtures.userDetailsEvent;
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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller) .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatViewUserRendersCorrectly() throws Exception {

        when(userService.requestUserDetails(any(RequestUserDetailsEvent.class))).thenReturn(
                userDetailsEvent(MY_ID));

        this.mockMvc.perform(get("/users/{uid}", MY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.uid").value(MY_ID.intValue()));
    }

}
