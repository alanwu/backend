package com.backend.core.repository;

import com.backend.core.domain.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UsersMemoryRepository implements UsersRepository {

    private Map<Long, User> users;

    public UsersMemoryRepository(final Map<Long, User> users) {
        this.users = Collections.unmodifiableMap(users);
    }

    @Override
    public synchronized User save(User user) {

        Map<Long, User> modifiableUsers = new HashMap<Long, User>(users);
        Random random = new Random();
        user.setUid(random.nextLong());
        modifiableUsers.put(user.getUid(), user);
        this.users = Collections.unmodifiableMap(modifiableUsers);

        return user;
    }

    @Override
    public synchronized void delete(long uid) {
        if (users.containsKey(uid)) {
            Map<Long, User> modifiableUsers = new HashMap<Long, User>(users);
            modifiableUsers.remove(uid);
            this.users = Collections.unmodifiableMap(modifiableUsers);
        }
    }

    @Override
    public User findById(long uid) {
        return users.get(uid);
    }

}
