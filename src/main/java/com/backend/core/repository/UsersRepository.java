package com.backend.core.repository;

import com.backend.core.domain.User;

/**
 * Created by alan on 2014-06-10.
 */
public interface UsersRepository {

    User save(User user);

    void delete(long uid);

    User findById(long uid);

}
