package com.backend.rest.controller;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.PostDetails;
import com.backend.core.events.posts.PostDetailsEvent;
import com.backend.core.events.posts.RequestAllPostsEvent;
import com.backend.core.events.posts.RequestPostDetailsEvent;
import com.backend.core.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alanw on 10/06/2014.
 */

@Controller
@RequestMapping("/posts")
public class PostQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(PostQueriesController.class);

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<Post>();
        for (PostDetails detail : postService.requestAllPosts(new RequestAllPostsEvent()).getPostsDetails()) {
            posts.add(Post.fromPostDetails(detail));
        }
        return posts;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Post> viewPost(@PathVariable String id) {

        PostDetailsEvent details = postService.requestPostDetails(new RequestPostDetailsEvent(Long.parseLong(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }

        Post post = Post.fromPostDetails(details.getPostDetails());

        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
    
}
