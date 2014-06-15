package com.backend.config;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.AllPostsEvent;
import com.backend.core.events.posts.CreatePostEvent;
import com.backend.core.events.posts.RequestAllPostsEvent;
import com.backend.core.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by alan on 2014-06-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
public class CoreDomainIntegrationTest {

    @Autowired
    PostService postService;

    @Test
    public void addANewPostToTheSystem() {
        CreatePostEvent ev = new CreatePostEvent(new Post());

        postService.createPost(ev);

        AllPostsEvent allPosts = postService.requestAllPosts(new RequestAllPostsEvent());

        assertEquals(1, allPosts.getPosts().size());

    }
}
