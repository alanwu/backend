package com.backend.core.service;

import com.backend.config.JPAConfig;
import com.backend.core.domain.User;
import com.backend.core.events.users.*;
import com.backend.core.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {JPAConfig.class})
public class UserEventHandler implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    public UserEventHandler() {
        //this.usersRepository = usersRepository;
    }

    @Override
    public UserCreatedEvent createUser(CreateUserEvent createUserEvent) {
        User user = (User) createUserEvent.getNewObject();
        user = usersRepository.save(user);

        return new UserCreatedEvent(user.getUid(), user);
    }

    @Override
    public UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent) {
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
