package com.mouvie.library.repository;

import com.mouvie.library.model.Movie;
import com.mouvie.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleLibRepository extends JpaRepository<Role, String> {
}
