package com.backend.rest.controller.user;

import com.backend.core.events.users.DeleteUserEvent;
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

import static com.backend.rest.controller.fixture.user.RestDataFixture.MY_ID;
import static com.backend.rest.controller.fixture.user.RestEventFixtures.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alanw on 10/06/2014.
 */
public class DeleteUserTest {

    MockMvc mockMvc;

    @InjectMocks
    UserCommandsController controller;

    @Mock
    UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller) .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @Test
    public void thatDeleteUserUsesHttpOkOnSuccess() throws Exception {

        when(userService.deleteUser(any(DeleteUserEvent.class)))
                .thenReturn(
                        userDeleted(MY_ID));

        this.mockMvc.perform(
                delete("/users/{uid}", String.valueOf(MY_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).deleteUser(argThat(
                Matchers.<DeleteUserEvent>hasProperty("uid", Matchers.equalTo(MY_ID))));
    }

    @Test
    public void thatDeleteUserUsesHttpNotFoundOnEntityLookupFailure() throws Exception {

        when(userService.deleteUser(any(DeleteUserEvent.class)))
                .thenReturn(
                        userDeletedNotFound(MY_ID));

        this.mockMvc.perform(
                delete("/users/{uid}", String.valueOf(MY_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void thatDeleteUserUsesHttpForbiddenOnEntityDeletionFailure() throws Exception {

        when(userService.deleteUser(any(DeleteUserEvent.class)))
                .thenReturn(
                        userDeletedFailed(MY_ID));

        this.mockMvc.perform(
                delete("/users/{uid}", String.valueOf(MY_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
