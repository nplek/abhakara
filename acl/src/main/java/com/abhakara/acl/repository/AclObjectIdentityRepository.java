package com.abhakara.acl.repository;

import com.abhakara.acl.entity.acl.AclObjectIdentity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AclObjectIdentityRepository extends JpaRepository<AclObjectIdentity,Long> {
    
}