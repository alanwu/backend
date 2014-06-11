package com.backend.core.service;

import com.backend.core.events.posts.*;

/**
 * Created by alanw on 10/06/2014.
 */
public interface PostService {

    public AllPostsEvent requestAllPosts(RequestAllPostsEvent requestAllCurrentPostsEvent);

    public PostDetailsEvent requestPostDetails(RequestPostDetailsEvent requestPostDetailsEvent);

    public PostCreatedEvent createPost(CreatePostEvent event);

    public PostDeletedEvent deletePost(DeletePostEvent deletePostEvent);

}
