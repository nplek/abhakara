package com.abhakara.admiralapi.service;

import java.util.List;
import java.util.Optional;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Log
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    //@Cacheable(value = "user", key = "#id", unless = "#result==null")
    public Optional<ABKUser> getUserById(int id) {
        log.info("UserService getUserById: " + id);
        return userRepository.findById(id);
    }
    
    @Cacheable(value = "userByEmail", key = "#email", unless = "#result==null")
    public List<ABKUser> getUserByEmail(String email) {
        log.info("UserService getUserByEmail:" + email);
        return userRepository.findByEmail(email);
    }

    @Cacheable(value="userPage", key="T(java.lang.String).format('O:%s-L:%s',#offset, #limit)", unless = "#result==null")
    public Page<ABKUser> getPages(int offset,int limit) {
        log.info("UserService getPages offset: " + offset + ", limit: " + limit);
        return userRepository.findAll(PageRequest.of(offset, limit));
    }

    @Caching(evict = { @CacheEvict(value = "userPage", allEntries = true )})
	public ABKUser addUser(ABKUser user){
		log.info("Add user: " + user.getName() );
		return userRepository.save(user);
	}

	@Caching(evict = { @CacheEvict(value = "userPage", allEntries = true )})
	@CachePut(value = "user", key = "#id")
	public Optional<ABKUser> updateUser(int id, ABKUser user) {
		log.info("Update user :" + id);
		Optional<ABKUser> userOpt = userRepository.findById(id);
        if(!userOpt.isPresent()) {
            return Optional.empty();
        }
        user.setId(id);
		return Optional.of(userRepository.save(user));
	}

	@Caching(evict = { @CacheEvict(value = "userPage", allEntries = true ), @CacheEvict(value = "user", key = "#id")})
	public boolean deleteUser(int id) {
        log.info("Delete user id: " + id);
		try {
            userRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
	}

}