package com.backend.rest.controller;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.PostDetailsEvent;
import com.backend.core.events.posts.RequestPostDetailsEvent;
import com.backend.core.service.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alanw on 10/06/2014.
 */

@Controller
@RequestMapping("/posts")
public class PostQueriesController {

    private static Logger LOG = Logger.getLogger(PostQueriesController.class);

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Post> viewPost(@PathVariable String id) {

        PostDetailsEvent details = postService.requestPostDetails(new RequestPostDetailsEvent(Long.parseLong(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }

        Post post = (Post) details.getDetails();

        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
    
}
