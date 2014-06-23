package com.backend.config;

import com.backend.core.repository.MJdbcTokenRepositoryImpl;
import com.backend.core.service.MPersistentTokenBasedRememberMeServices;
import com.backend.rest.filter.MRememberMeAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import javax.sql.DataSource;

/**
 * Created by alan on 2014-06-11.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String KEY = "1234";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select email as username, password, 1 as enabled from user where email = ?")
                .authoritiesByUsernameQuery("select user.email as username, user_role.role from user, user_role where user.uid = user_role.user_uid and user.email = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        RememberMeServices rememberMeServices = rememberMeServices(http);
        AuthenticationManager authenticationManager = authenticationManager(http);

        http.authorizeRequests()
                .antMatchers("/posts/**").hasRole("USER")
                .anyRequest().anonymous()
            .and()
                .formLogin()
            .and()
                .rememberMe().tokenRepository(persistentTokenRepository()).rememberMeServices(rememberMeServices)
                .tokenValiditySeconds(1209600).key(KEY)
            .and()
                .csrf()
                .disable()
                .addFilterAfter(new MRememberMeAuthenticationFilter(authenticationManager, rememberMeServices), RememberMeAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        MJdbcTokenRepositoryImpl db = new MJdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }

    public RememberMeServices rememberMeServices(HttpSecurity http) {
        UserDetailsService userDetailsService = http.getSharedObject(UserDetailsService.class);
        MPersistentTokenBasedRememberMeServices mPersistentTokenBasedRememberMeServices = new MPersistentTokenBasedRememberMeServices(KEY, userDetailsService, persistentTokenRepository());
        return mPersistentTokenBasedRememberMeServices;
    }

    public AuthenticationManager authenticationManager(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        return authenticationManager;
    }

}