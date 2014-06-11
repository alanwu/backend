package com.backend.core.events.posts;

import com.backend.core.events.UpdatedEvent;

public class PostUpdatedEvent extends UpdatedEvent {

  private long id;
  private PostDetails postDetails;

  public PostUpdatedEvent(long id, PostDetails postDetails) {
    this.id = id;
    this.postDetails = postDetails;
  }

  public long getId() {
    return id;
  }

  public PostDetails getPostDetails() {
    return postDetails;
  }
}
