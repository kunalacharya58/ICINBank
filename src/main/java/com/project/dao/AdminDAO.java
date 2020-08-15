package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Admin;

@Repository
public interface AdminDAO extends CrudRepository<Admin, Long> {
	
	Admin findByUsername(String username);

}
