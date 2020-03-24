package com.abhakara.acl.entity.acl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

public class CustomPermission extends BasePermission {

    public static final Permission ADMIN_READ = new CustomPermission(1 << 5, 'M');

    public CustomPermission(int mask, char code) {
        super(mask, code);
    }

    private static final long serialVersionUID = -7695655824830259000L;

}