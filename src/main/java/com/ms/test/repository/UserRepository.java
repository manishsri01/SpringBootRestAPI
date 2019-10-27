package com.ms.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.ms.test.beans.User;

/**
 * UserRepository
 * @author Manish
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
	
}