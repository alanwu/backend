package com.backend.core.service;

import com.backend.core.events.users.*;

/**
 * Created by alanw on 10/06/2014.
 */
public interface UserService {

    public UserDetailsEvent getUserDetails(RequestUserDetailsEvent requestUserDetailsEvent);

    public UserCreatedEvent createUser(CreateUserEvent createUserEvent);

    public UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent) throws Exception;

    public UserDeletedEvent deleteUser(DeleteUserEvent deleteUserEvent);

}
