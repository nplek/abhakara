package com.abhakara.acl.service;

import com.abhakara.acl.entity.AbkUser;
import com.abhakara.acl.entity.AbkUserDetails;
import com.abhakara.acl.repository.AbkUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AbkUserDetailsService implements UserDetailsService {

    @Autowired
    private AbkUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbkUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }
        return new AbkUserDetails(user);
    }

}