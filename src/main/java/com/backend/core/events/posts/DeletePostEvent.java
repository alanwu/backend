package com.backend.core.events.posts;

import com.backend.core.events.DeleteEvent;

public class DeletePostEvent extends DeleteEvent {

  private final long id;

  public DeletePostEvent(final long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
