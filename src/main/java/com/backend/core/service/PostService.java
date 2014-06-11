package com.backend.core.service;

import com.backend.core.events.posts.CreatePostEvent;
import com.backend.core.events.posts.PostCreatedEvent;
import com.backend.core.events.posts.PostDetailsEvent;
import com.backend.core.events.posts.RequestPostDetailsEvent;

/**
 * Created by alanw on 10/06/2014.
 */
public interface PostService {

    public PostDetailsEvent requestPostDetails(RequestPostDetailsEvent requestPostDetailsEvent);

    public PostCreatedEvent createPost(CreatePostEvent event);

}
