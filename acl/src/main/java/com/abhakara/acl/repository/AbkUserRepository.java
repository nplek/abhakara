package com.abhakara.acl.repository;

import com.abhakara.acl.entity.AbkUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AbkUserRepository extends JpaRepository<AbkUser,Long> {

    AbkUser findByEmail(String email);
}