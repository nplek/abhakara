package com.abhakara.acl.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public final class AbkUserDetails extends AbkUser implements UserDetails {
    
    public AbkUserDetails(AbkUser user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPassword(user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.getRoles().isEmpty()){
            return AbkUserAuthorityUtils.createAuthorities(this);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : this.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
        
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static final long serialVersionUID = 3384436451564509032L;
}