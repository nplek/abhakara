package com.abhakara.admiralapi.service;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.entity.UserPrincipal;
import com.abhakara.admiralapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ABKUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        ABKUser user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}