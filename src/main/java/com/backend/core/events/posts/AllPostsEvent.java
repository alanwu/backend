package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllPostsEvent extends ReadEvent {

  private final List<Post> posts;

  public AllPostsEvent(List<Post> posts) {
    this.posts = Collections.unmodifiableList(posts);
  }

  public Collection<Post> getPosts() {
    return this.posts;
  }

}
