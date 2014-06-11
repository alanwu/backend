package com.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by alan on 2014-06-10.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.backend.rest.controller"})
public class MVCConfig {

}
