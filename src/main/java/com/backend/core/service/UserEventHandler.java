package com.backend.core.service;

import com.backend.config.JPAConfig;
import com.backend.core.domain.User;
import com.backend.core.domain.UserRole;
import com.backend.core.events.users.*;
import com.backend.core.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

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
        user.setPassword(passwordEncoder.encode(user.getClearTextPassword()));
        user.getUserRoles().add(new UserRole("ROLE_USER"));

        user = usersRepository.save(user);

        return new UserCreatedEvent(user.getUid(), user);
    }

    @Override
    public UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent) throws Exception {
        User userToUpdate = (User) updateUserEvent.getUpdateObject();

        long userCountWithSameEmail = usersRepository.getUserCountWithSameEmail(userToUpdate.getEmail(), userToUpdate.getUid());
        if (userCountWithSameEmail > 0L) {
            throw new Exception("User with the given email already exist");
        }
        User userFromDb = usersRepository.findByUid(userToUpdate.getUid());
        if (userFromDb == null) {
            throw new Exception("User with the given uid doesn't exist");
        }

        userFromDb.setEmail(userToUpdate.getEmail());
        userFromDb.setFirstName(userToUpdate.getFirstName());
        userFromDb.setLastName(userToUpdate.getLastName());
        userFromDb.setGender(userToUpdate.getGender());
        userFromDb.setYearOfBirth(userToUpdate.getYearOfBirth());

        User updatedUser = usersRepository.save(userFromDb);

        return new UserUpdatedEvent(updatedUser);
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
