package com.abhakara.admiralapi.repository;

import com.abhakara.admiralapi.entity.ABKUser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<ABKUser, Integer> {
    @Query(value ="SELECT t.* FROM users t where t.email LIKE lower(CONCAT('%', :email, '%'))", nativeQuery = true)
    List<ABKUser> findByEmail(@Param("email") String email);

    @Query(value = "SELECT t.* FROM users t WHERE t.name = :name", nativeQuery = true)
    ABKUser findByName(@Param("name") String name);
}