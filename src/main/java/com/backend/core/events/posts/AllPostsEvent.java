package com.backend.core.events.posts;

import com.backend.core.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllPostsEvent extends ReadEvent {

  private final List<PostDetails> postsDetails;

  public AllPostsEvent(List<PostDetails> posts) {
    this.postsDetails = Collections.unmodifiableList(posts);
  }

  public Collection<PostDetails> getPostsDetails() {
    return this.postsDetails;
  }

}
