package com.abhakara.admiralapi.repository;

import com.abhakara.admiralapi.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    @Query(value ="SELECT t.id, t.name, t.password, t.email, t.enabled FROM users t where t.email LIKE lower(CONCAT('%', :email, '%'))", nativeQuery = true)
    List<User> findByEmail(@Param("email") String email);

    //@Query(value = "SELECT t.id, t.name, t.password, t.email, t.enabled FROM users t WHERE t.name LIKE lower(CONCAT('%',:name,'%))'", nativeQuery = true)
    //Optional<User> findByName(@Param("name") String name);
}