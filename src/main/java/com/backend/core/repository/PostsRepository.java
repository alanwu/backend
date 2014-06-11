package com.backend.core.repository;

import com.backend.core.domain.Post;

import java.util.List;

/**
 * Created by alan on 2014-06-10.
 */
public interface PostsRepository {

    Post save(Post post);

    void delete(long id);

    Post findById(long id);

    List<Post> findAll();
    
}