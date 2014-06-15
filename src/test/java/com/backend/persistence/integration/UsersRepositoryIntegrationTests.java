package com.backend.persistence.integration;

import com.backend.config.JPAConfig;
import com.backend.core.domain.User;
import com.backend.core.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by alan on 2014-06-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UsersRepositoryIntegrationTests {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {
        User user = new User();
        user.setFirstName("Alan");
        user.setLastName("Wu");
        user.setEmail("alanwunan@gmail.com");

        usersRepository.save(user);

        User retrievedUser = usersRepository.findByUid(user.getUid());

        assertNotNull(retrievedUser);
    }

}
