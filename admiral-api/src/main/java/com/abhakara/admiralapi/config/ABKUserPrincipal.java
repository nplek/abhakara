package com.abhakara.admiralapi.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.entity.Privilege;
import com.abhakara.admiralapi.entity.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ABKUserPrincipal implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -2603085387405809563L;
    private ABKUser user;

    public ABKUserPrincipal(ABKUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role: user.getRoles()) {
            for (Privilege privilege: role.getPrivileges()) {
				authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public ABKUser getUser() {
        return this.user;
    }

}