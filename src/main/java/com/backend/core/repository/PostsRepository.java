package com.backend.core.repository;

import com.backend.core.domain.Post;
import com.backend.core.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by alan on 2014-06-10.
 */
public interface PostsRepository extends PagingAndSortingRepository<Post, Long> {

    Post findByUid(long uid);

    Post findByUser(User user);

}
