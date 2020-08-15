package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.AdminDAO;
import com.project.model.Admin;
import com.project.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO adminDao;

	@Override
	public Admin getAdminByUsername(String username) {
		return adminDao.findByUsername(username);
	}

	@Override
	public Admin getAdminById(long adminId) {
		return adminDao.findById(adminId).orElse(null);
	}

	@Override
	public Admin createAdmin(Admin admin) {
		return adminDao.save(admin);
	}

}
