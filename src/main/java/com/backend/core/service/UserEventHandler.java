package com.backend.core.service;

import com.backend.config.JPAConfig;
import com.backend.core.domain.UserRole;
import com.backend.core.domain.User;
import com.backend.core.events.users.*;
import com.backend.core.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.Calendar;
import java.util.GregorianCalendar;

@ContextConfiguration(classes = {JPAConfig.class})
public class UserEventHandler implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEventHandler() {

    }

    @Override
    public UserCreatedEvent createUser(CreateUserEvent createUserEvent) {
        User user = (User) createUserEvent.getNewObject();
        Calendar today = new GregorianCalendar();
        user.setPassword(passwordEncoder.encode(user.getClearTextPassword()));
        user.setCreatedDate(today.getTime());
        user.setLastModifiedDate(today.getTime());
        user.getUserRoles().add(new UserRole("ROLE_USER"));

        user = usersRepository.save(user);

        return new UserCreatedEvent(user.getUid(), user);
    }

    @Override
    public UserDetailsEvent getUserDetails(RequestUserDetailsEvent requestUserDetailsEvent) {
        User user = usersRepository.findByUid(requestUserDetailsEvent.getUid());

        if (user == null) {
            return UserDetailsEvent.notFound(requestUserDetailsEvent.getUid());
        }

        return new UserDetailsEvent(requestUserDetailsEvent.getUid(), user);
    }

    @Override
    public UserDeletedEvent deleteUser(DeleteUserEvent deleteUserEvent) {
        User user = usersRepository.findByUid(deleteUserEvent.getUid());

        if (user == null) {
            return UserDeletedEvent.notFound(deleteUserEvent.getUid());
        }

        if (!user.canBeDeleted()) {
            return UserDeletedEvent.deletionForbidden(deleteUserEvent.getUid(), user);
        }

        usersRepository.delete(deleteUserEvent.getUid());

        return new UserDeletedEvent(deleteUserEvent.getUid(), user);
    }

}
