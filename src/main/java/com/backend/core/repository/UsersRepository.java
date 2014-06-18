package com.backend.core.repository;

import com.backend.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alan on 2014-06-10.
 */
public interface UsersRepository extends CrudRepository<User, Long> {

    //User save(User user);

    //void delete(long uid);

    User findByUid(long uid);

    User findByEmail(String email);

    @Query("select count(u.uid) from USER u where u.email = ?1 and u.uid != ?2")
    long getUserCountWithSameEmail(String email, long uid);

}
