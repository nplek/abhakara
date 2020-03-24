package com.abhakara.acl.repository;

import com.abhakara.acl.entity.acl.AclSid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AclSidRepository extends JpaRepository<AclSid,Long> {
    
}