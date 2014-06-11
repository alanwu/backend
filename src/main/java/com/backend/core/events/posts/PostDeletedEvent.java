package com.backend.core.events.posts;


import com.backend.core.events.DeletedEvent;

public class PostDeletedEvent extends DeletedEvent {

  private long id;
  private PostDetails details;
  private boolean deletionCompleted;

  private PostDeletedEvent(long id) {
    this.id = id;
  }

  public PostDeletedEvent(long id, PostDetails details) {
    this.id = id;
    this.details = details;
    this.deletionCompleted = true;
  }

  public long getId() {
    return id;
  }

  public PostDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static PostDeletedEvent deletionForbidden(long id, PostDetails details) {
    PostDeletedEvent ev = new PostDeletedEvent(id, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static PostDeletedEvent notFound(long id) {
    PostDeletedEvent ev = new PostDeletedEvent(id);
    ev.entityFound=false;
    return ev;
  }
}
