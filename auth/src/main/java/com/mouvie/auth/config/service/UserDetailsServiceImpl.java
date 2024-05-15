package com.mouvie.auth.config.service;

import com.mouvie.auth.config.appcontext.AppContext;
import com.mouvie.auth.config.security.CustomUserDetails;
import com.mouvie.auth.repository.user.UserRepository;
import com.mouvie.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppContext appContext;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("could not found user..!!");
        }

        appContext.setCurrentUser(user);

        return new CustomUserDetails(user);
    }
}
