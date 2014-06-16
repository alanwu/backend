package com.backend.config;

import com.backend.core.domain.User;
import com.backend.core.events.users.CreateUserEvent;
import com.backend.core.events.users.RequestUserDetailsEvent;
import com.backend.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by alan on 2014-06-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class, JPAConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CoreDomainIntegrationTest {

    @Autowired
    UserService userService;

    @Test
    public void addANewUserToTheSystem() {
        User newUser = new User();
        newUser.setFirstName("Alan");
        newUser.setLastName("Wu");
        newUser.setEmail("alanwunan@gmail.com");

        CreateUserEvent ev = new CreateUserEvent(newUser);

        userService.createUser(ev);

        User user = (User) userService.getUserDetails(new RequestUserDetailsEvent(ev.getNewObject().getUid())).getDetails();

        assertNotNull(user);

    }
}
