package com.backend.rest.controller.post;

import com.backend.core.events.posts.DeletePostEvent;
import com.backend.core.service.PostService;
import com.backend.rest.controller.PostCommandsController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.backend.rest.controller.fixture.post.RestDataFixture.MY_ID;
import static com.backend.rest.controller.fixture.post.RestEventFixtures.*;
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
public class DeletePostTest {

    MockMvc mockMvc;

    @InjectMocks
    PostCommandsController controller;

    @Mock
    PostService postService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @Test
    public void thatDeletePostUsesHttpOkOnSuccess() throws Exception {

        when(postService.deletePost(any(DeletePostEvent.class)))
                .thenReturn(
                        postDeleted(MY_ID));

        this.mockMvc.perform(
                delete("/posts/{uid}", String.valueOf(MY_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(postService).deletePost(argThat(
                Matchers.<DeletePostEvent>hasProperty("uid", Matchers.equalTo(MY_ID))));
    }

    @Test
    public void thatDeletePostUsesHttpNotFoundOnEntityLookupFailure() throws Exception {

        when(postService.deletePost(any(DeletePostEvent.class)))
                .thenReturn(
                        postDeletedNotFound(MY_ID));

        this.mockMvc.perform(
                delete("/posts/{uid}", String.valueOf(MY_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void thatDeletePostUsesHttpForbiddenOnEntityDeletionFailure() throws Exception {

        when(postService.deletePost(any(DeletePostEvent.class)))
                .thenReturn(
                        postDeletedFailed(MY_ID));

        this.mockMvc.perform(
                delete("/posts/{uid}", String.valueOf(MY_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
