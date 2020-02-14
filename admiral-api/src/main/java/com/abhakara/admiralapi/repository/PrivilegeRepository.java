package com.abhakara.admiralapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.abhakara.admiralapi.entity.Privilege;

public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Long> {
}