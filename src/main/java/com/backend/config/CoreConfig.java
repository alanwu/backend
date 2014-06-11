package com.backend.config;

import com.backend.core.domain.Post;
import com.backend.core.repository.PostsMemoryRepository;
import com.backend.core.repository.PostsRepository;
import com.backend.core.service.PostEventHandler;
import com.backend.core.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * Created by alan on 2014-06-10.
 */
@Configuration
public class CoreConfig {

    @Bean
    public PostService createService(PostsRepository repo) {
        return new PostEventHandler(repo);
    }

    @Bean
    public PostsRepository createRepo() {
        return new PostsMemoryRepository(new HashMap<Long, Post>());
    }

}