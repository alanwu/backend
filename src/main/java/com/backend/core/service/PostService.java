package com.backend.core.service;

import com.backend.core.events.posts.*;

/**
 * Created by alanw on 10/06/2014.
 */
public interface PostService {

    public PostDetailsEvent requestPostDetails(RequestPostDetailsEvent requestPostDetailsEvent);

    public PostCreatedEvent createPost(CreatePostEvent createPostEvent);

    public PostDeletedEvent deletePost(DeletePostEvent deletePostEvent);

}
