package com.mouvie.security.service;

import com.mouvie.library.model.User;
import com.mouvie.security.config.appcontext.AppContext;
import com.mouvie.security.config.security.CustomUserDetails;
import com.mouvie.security.repository.UserRepository;
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
            throw new UsernameNotFoundException("Could not found user..!!");
        }

        appContext.setCurrentUser(user);

        return new CustomUserDetails(user);
    }
}
