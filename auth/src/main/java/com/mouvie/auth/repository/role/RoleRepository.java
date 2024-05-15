package com.mouvie.auth.repository.role;

import com.mouvie.library.model.Role;
import com.mouvie.library.repository.RoleLibRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends RoleLibRepository {

    @Query("FROM Role WHERE LOWER(name) LIKE LOWER(:name) ")
    Optional<Role> findByName(String name);

}
