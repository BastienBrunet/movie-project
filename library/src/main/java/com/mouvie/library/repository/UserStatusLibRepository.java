package com.mouvie.library.repository;

import com.mouvie.library.model.Movie;
import com.mouvie.library.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusLibRepository extends JpaRepository<UserStatus, String> {
}
