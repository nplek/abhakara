package com.abhakara.admiralapi.config;

import java.io.Serializable;

import com.abhakara.admiralapi.entity.ABKUser;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        //System.out.println("---> hasPermission <---");
        //System.out.println("---> hasPermission " + authentication.getName() + "<---");
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
         
        return hasPrivilege(authentication, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        //System.out.println("---> hasPermission targetType:" + targetType + "<---");
        //System.out.println("---> hasPermission username:" + authentication.getName() + "<---");
        if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        System.out.println(">>>>>>>>>>>>>CustomPermissionEvaluator<<<<<<<<<<<<<<<<");
        //ABKUser urs = ((ABKUserPrincipal)auth.getPrincipal()).getUser();
        //System.out.println("==>" + urs + "<==");
        System.out.println("---> Username:" + auth.getName() + "<---");
        System.out.println("---> Authorities:" + auth.getAuthorities() + "<---");
        System.out.println("---> TargetType:" +targetType + " <---");
        System.out.println("---> Permission:" + permission + "<---");
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            if (grantedAuth.getAuthority().startsWith(targetType)) {
                if (grantedAuth.getAuthority().contains(permission)) {
                    System.out.println("---> Privilege :" + grantedAuth.getAuthority() +" <---");
                    return true;
                }
            }
        }
        System.out.println("---> Privilege not found <---");
        return false;
    }

}