package com.abhakara.admiralapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.abhakara.admiralapi.entity.Organization;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
}