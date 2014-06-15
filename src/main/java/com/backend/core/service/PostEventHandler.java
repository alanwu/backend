package com.backend.core.service;

import com.backend.core.domain.Post;
import com.backend.core.events.posts.*;
import com.backend.core.repository.PostsRepository;

import java.util.ArrayList;
import java.util.List;

public class PostEventHandler implements PostService {

    private final PostsRepository postsRepository;

    public PostEventHandler(final PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public PostCreatedEvent createPost(CreatePostEvent createPostEvent) {
        Post post = createPostEvent.getPost();
        post = postsRepository.save(post);

        return new PostCreatedEvent(post.getUid(), post);
    }

    @Override
    public AllPostsEvent requestAllPosts(RequestAllPostsEvent requestAllCurrentPostsEvent) {
        List<Post> generatedPosts = new ArrayList<Post>();

        return new AllPostsEvent(postsRepository.findAll());
    }

    @Override
    public PostDetailsEvent requestPostDetails(RequestPostDetailsEvent requestPostDetailsEvent) {
        Post post = postsRepository.findById(requestPostDetailsEvent.getUid());

        if (post == null) {
            return PostDetailsEvent.notFound(requestPostDetailsEvent.getUid());
        }

        return new PostDetailsEvent(requestPostDetailsEvent.getUid(), post);
    }

    @Override
    public PostDeletedEvent deletePost(DeletePostEvent deletePostEvent) {
        Post post = postsRepository.findById(deletePostEvent.getUid());

        if (post == null) {
            return PostDeletedEvent.notFound(deletePostEvent.getUid());
        }

        if (!post.canBeDeleted()) {
            return PostDeletedEvent.deletionForbidden(deletePostEvent.getUid(), post);
        }

        postsRepository.delete(deletePostEvent.getUid());

        return new PostDeletedEvent(deletePostEvent.getUid(), post);
    }

}
