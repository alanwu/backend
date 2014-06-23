package com.backend.config;

import com.backend.core.service.PostEventHandler;
import com.backend.core.service.PostService;
import com.backend.core.service.UserEventHandler;
import com.backend.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan on 2014-06-10.
 */
@Configuration
public class CoreConfig {

    @Bean
    public PostService createPostService() {
        return new PostEventHandler();
    }

    @Bean
    public UserService createUserService() {
        return new UserEventHandler();
    }

}
