package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
	public User findByEmail(String email);

}
