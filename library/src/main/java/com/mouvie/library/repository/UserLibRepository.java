package com.mouvie.library.repository;

import com.mouvie.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLibRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
