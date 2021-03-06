package com.backend.rest.controller.post;

import com.backend.core.events.posts.RequestPostDetailsEvent;
import com.backend.core.service.PostService;
import com.backend.rest.controller.PostQueriesController;
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
import static com.backend.rest.controller.fixture.post.RestEventFixtures.postDetailsEvent;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alanw on 10/06/2014.
 */
public class ViewPostTest {

    MockMvc mockMvc;

    @InjectMocks
    PostQueriesController controller;

    @Mock
    PostService postService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller) .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatViewPostRendersCorrectly() throws Exception {

        when(postService.requestPostDetails(any(RequestPostDetailsEvent.class))).thenReturn(
                postDetailsEvent(MY_ID));

        this.mockMvc.perform(get("/posts/{uid}", MY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text").value(MY_POST))
                .andExpect(jsonPath("$.uid").value(MY_ID.intValue()));
    }

}
