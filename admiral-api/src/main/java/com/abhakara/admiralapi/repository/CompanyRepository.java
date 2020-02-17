package com.abhakara.admiralapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.abhakara.admiralapi.entity.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}