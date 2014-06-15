package com.backend.rest.controller;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.CreatePostEvent;
import com.backend.core.events.posts.DeletePostEvent;
import com.backend.core.events.posts.PostCreatedEvent;
import com.backend.core.events.posts.PostDeletedEvent;
import com.backend.core.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

        PostCreatedEvent postCreatedEvent = postService.createPost(new CreatePostEvent(post));

        Post newPost = postCreatedEvent.getPost();

        HttpHeaders headers = new HttpHeaders();
        URI location = builder.path("/posts/{id}").buildAndExpand(String.valueOf(postCreatedEvent.getNewPostUid())).toUri();
        headers.setLocation(location);

        return new ResponseEntity<Post>(newPost, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Post> cancelPost(@PathVariable String id) {

        PostDeletedEvent postDeletedEvent = postService.deletePost(new DeletePostEvent(Long.parseLong(id)));

        if (!postDeletedEvent.isEntityFound()) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }

        Post post = postDeletedEvent.getPost();

        if (postDeletedEvent.isDeletionCompleted()) {
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }

        return new ResponseEntity<Post>(post, HttpStatus.FORBIDDEN);
    }

}
