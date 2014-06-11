package com.backend.rest.controller;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.CreatePostEvent;
import com.backend.core.events.posts.PostCreatedEvent;
import com.backend.core.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by alanw on 10/06/2014.
 */

@Controller
@RequestMapping("/posts")
public class PostCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(PostCommandsController.class);

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> createOrder(@RequestBody Post post, UriComponentsBuilder builder) {

        PostCreatedEvent postCreated = postService.createPost(new CreatePostEvent(post.toPostDetails()));

        Post newPost = Post.fromPostDetails(postCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/posts/{id}")
                        .buildAndExpand(postCreated.getNewPostKey().toString()).toUri());

        return new ResponseEntity<Post>(newPost, headers, HttpStatus.CREATED);
    }

}