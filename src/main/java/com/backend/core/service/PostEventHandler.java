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
    Post post = Post.fromPostDetails(createPostEvent.getDetails());

    post = postsRepository.save(post);

    return new PostCreatedEvent(post.getId(), post.toPostDetails());
  }

  @Override
  public AllPostsEvent requestAllPosts(RequestAllPostsEvent requestAllCurrentPostsEvent) {
    List<PostDetails> generatedDetails = new ArrayList<PostDetails>();
    for (Post post : postsRepository.findAll()) {
      generatedDetails.add(post.toPostDetails());
    }
    return new AllPostsEvent(generatedDetails);
  }

  @Override
  public PostDetailsEvent requestPostDetails(RequestPostDetailsEvent requestPostDetailsEvent) {

    Post post = postsRepository.findById(requestPostDetailsEvent.getId());

    if (post == null) {
      return PostDetailsEvent.notFound(requestPostDetailsEvent.getId());
    }

    return new PostDetailsEvent(
            requestPostDetailsEvent.getId(),
            post.toPostDetails());
  }

  @Override
  public PostDeletedEvent deletePost(DeletePostEvent deletePostEvent) {

    Post post = postsRepository.findById(deletePostEvent.getId());

    if (post == null) {
      return PostDeletedEvent.notFound(deletePostEvent.getId());
    }

    PostDetails details = post.toPostDetails();

    //TODOCUMENT This contains some specific domain logic, not exposed to the outside world, and not part of the
    //persistence rules.

    if (!post.canBeDeleted()) {
      return PostDeletedEvent.deletionForbidden(deletePostEvent.getId(), details);
    }

    postsRepository.delete(deletePostEvent.getId());
    return new PostDeletedEvent(deletePostEvent.getId(), details);
  }

}
