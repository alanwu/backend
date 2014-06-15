package com.backend.config;

import com.backend.core.domain.Post;
import com.backend.core.domain.User;
import com.backend.core.repository.PostsMemoryRepository;
import com.backend.core.repository.PostsRepository;
import com.backend.core.repository.UsersMemoryRepository;
import com.backend.core.repository.UsersRepository;
import com.backend.core.service.PostEventHandler;
import com.backend.core.service.PostService;
import com.backend.core.service.UserEventHandler;
import com.backend.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * Created by alan on 2014-06-10.
 */
@Configuration
public class CoreConfig {

    @Bean
    public PostService createPostService(PostsRepository repo) {
        return new PostEventHandler(repo);
    }

    @Bean
    public PostsRepository createPostRepo() {
        return new PostsMemoryRepository(new HashMap<Long, Post>());
    }

    @Bean
    public UserService createUserService(UsersRepository repo) {
        return new UserEventHandler(repo);
    }

    @Bean
    public UsersRepository createUserRepo() {
        return new UsersMemoryRepository(new HashMap<Long, User>());
    }

}
