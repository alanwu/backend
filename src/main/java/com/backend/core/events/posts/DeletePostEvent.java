package com.backend.core.events.posts;

import com.backend.core.events.DeleteEvent;

public class DeletePostEvent extends DeleteEvent {

  private final long uid;

  public DeletePostEvent(final long uid) {
    this.uid = uid;
  }

  public long getUid() {
    return uid;
  }
}
