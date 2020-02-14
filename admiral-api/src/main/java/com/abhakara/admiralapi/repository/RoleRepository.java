package com.abhakara.admiralapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.abhakara.admiralapi.entity.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}