package com.backend.persistence.integration;

import com.backend.config.JPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static com.backend.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static com.backend.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

/**
 * Created by alan on 2014-06-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserMappingIntegrationTests {

    @Autowired
    EntityManager entityManager;

    @Test
    public void thatItemCustomMappingWorks() throws Exception {
        assertTableExists(entityManager, "USER");

        assertTableHasColumn(entityManager, "USER", "UID");
        assertTableHasColumn(entityManager, "USER", "FIRST_NAME");
        assertTableHasColumn(entityManager, "USER", "LAST_NAME");
        assertTableHasColumn(entityManager, "USER", "EMAIL");
        assertTableHasColumn(entityManager, "USER", "PASSWORD");
        assertTableHasColumn(entityManager, "USER", "GENDER");
        assertTableHasColumn(entityManager, "USER", "CREATED_DATE");
        assertTableHasColumn(entityManager, "USER", "LAST_MODIFIED_DATE");
        assertTableHasColumn(entityManager, "USER", "YEAR_OF_BIRTH");
    }

}
