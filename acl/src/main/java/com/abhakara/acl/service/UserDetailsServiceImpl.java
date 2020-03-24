package com.abhakara.acl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import com.abhakara.acl.entity.AbkUser;
import com.abhakara.acl.entity.Role;
import com.abhakara.acl.repository.AbkUserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AbkUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        AbkUser user = userRepository.findByEmail(username);

        if (user == null)
            throw new UsernameNotFoundException("username " + username + " not found");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}