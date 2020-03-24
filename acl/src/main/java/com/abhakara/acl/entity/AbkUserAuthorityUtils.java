package com.abhakara.acl.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public final class AbkUserAuthorityUtils {
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN",
            "ROLE_USER");
    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

    public static Collection<? extends GrantedAuthority> createAuthorities(AbkUser abkUser) {
        String username = abkUser.getEmail();
        if (username.startsWith("admin")) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }

    private AbkUserAuthorityUtils() {
    }

}