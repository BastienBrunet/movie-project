package com.mouvie.auth.repository.user;

import com.mouvie.library.model.User;
import com.mouvie.library.repository.UserLibRepository;

public interface UserRepository extends UserLibRepository {

    User findByUsername(String username);
}
