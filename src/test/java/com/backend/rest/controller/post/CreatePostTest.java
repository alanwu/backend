package com.backend.rest.controller.post;

import com.backend.core.events.posts.CreatePostEvent;
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
import static com.backend.rest.controller.fixture.post.RestDataFixture.MY_POST;
import static com.backend.rest.controller.fixture.post.RestDataFixture.standardPostJSON;
import static com.backend.rest.controller.fixture.post.RestEventFixtures.postCreated;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alanw on 10/06/2014.
 */
public class CreatePostTest {

    MockMvc mockMvc;

    @InjectMocks
    PostCommandsController controller;

    @Mock
    PostService postService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller) .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

        when(postService.createPost(any(CreatePostEvent.class))).thenReturn(
                postCreated(MY_ID.longValue()));

    }

    @Test
    public void thatCreatePostUsesHttpCreated() throws Exception {

        this.mockMvc.perform(
                post("/posts")
                        .content(standardPostJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void thatCreatePostRendersAsJson() throws Exception {

        this.mockMvc.perform(
                post("/posts")
                        .content(standardPostJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text").value(MY_POST))
                .andExpect(jsonPath("$.uid").value(MY_ID.intValue()));
    }

    @Test
    public void thatCreatePostPassesLocationHeader() throws Exception {

        this.mockMvc.perform(
                post("/posts")
                        .content(standardPostJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", Matchers.endsWith("/posts/" + MY_ID.toString())));
    }

}
