package com.abhakara.admiralapi.config;

import org.springframework.security.core.Authentication;

public class WebSecurity {
    public boolean checkUserID(Authentication authentication, Long id) {
        //https://www.baeldung.com/spring-security-create-new-custom-security-expression
        return true;
    }
}