package com.backend.core.events.posts;

import com.backend.core.domain.Post;
import com.backend.core.events.UpdatedEvent;

public class PostUpdatedEvent extends UpdatedEvent {

  private long uid;
  private Post post;

  public PostUpdatedEvent(long uid, Post post) {
    this.uid = uid;
    this.post = post;
  }

  public long getUid() {
    return uid;
  }

  public Post getPost() {
    return post;
  }
}
