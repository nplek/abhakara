package com.abhakara.admiralapi.config;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.entity.Role;
import com.abhakara.admiralapi.service.ABKUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

public class CustomMethodSecurityExpressionRoot 
  extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
 
    @Autowired
    private ABKUserDetailsService abkUserDetailsService;
    
    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }
 
    public boolean isMember(Long OrganizationId) {
        //Error Cast
        System.out.println(this.getPrincipal());
        ABKUserPrincipal abk = (ABKUserPrincipal)this.getPrincipal();
        //ABKUser user = ((ABKUserPrincipal) this.getPrincipal()).getUser();
        /*UserDetails userPrincipal = (UserDetails) this.getPrincipal();
        ABKUser user = ((ABKUserPrincipal) userPrincipal).getUser();*/
        //System.out.println("UserPrincipal : " + user.getName());
        //return user.getOrganization().getId().longValue() == OrganizationId.longValue();
        /*for (Role role: user.getRoles()){
            if (role.getOrganization().getId().longValue() == OrganizationId.longValue()) {
                System.out.println("isMember Org:" + OrganizationId);
                return true;
            }
        }*/
        System.out.println("is NOT Member Org:" + OrganizationId);
        return false;
    }

    private Object filterObject;
    private Object returnObject;

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
 
}