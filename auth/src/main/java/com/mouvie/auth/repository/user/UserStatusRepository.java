package com.mouvie.auth.repository.user;

import com.mouvie.library.model.UserStatus;
import com.mouvie.library.repository.UserStatusLibRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserStatusRepository extends UserStatusLibRepository {

    @Query("FROM UserStatus WHERE LOWER(name) LIKE LOWER(:name) ")
    Optional<UserStatus> findByName(String name);
}
