package com.backend.core.service;

import com.backend.config.JPAConfig;
import com.backend.core.domain.Post;
import com.backend.core.events.posts.*;
import com.backend.core.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {JPAConfig.class})
public class PostEventHandler implements PostService {

    @Autowired
    private PostsRepository postsRepository;

    public PostEventHandler() {

    }

    @Override
    public PostCreatedEvent createPost(CreatePostEvent createPostEvent) {
        Post post = (Post) createPostEvent.getNewObject();
        post = postsRepository.save(post);

        return new PostCreatedEvent(post.getUid(), post);
    }

    @Override
    public PostDetailsEvent requestPostDetails(RequestPostDetailsEvent requestPostDetailsEvent) {
        Post post = postsRepository.findByUid(requestPostDetailsEvent.getUid());

        if (post == null) {
            return PostDetailsEvent.notFound(requestPostDetailsEvent.getUid());
        }

        return new PostDetailsEvent(requestPostDetailsEvent.getUid(), post);
    }

    @Override
    public PostDeletedEvent deletePost(DeletePostEvent deletePostEvent) {
        Post post = postsRepository.findByUid(deletePostEvent.getUid());

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
