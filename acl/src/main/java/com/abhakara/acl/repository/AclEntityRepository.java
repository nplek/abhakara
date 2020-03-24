package com.abhakara.acl.repository;

import com.abhakara.acl.entity.acl.AclEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AclEntityRepository extends JpaRepository<AclEntity,Long> {
    
}