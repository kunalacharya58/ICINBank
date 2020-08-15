package com.project.service;

import com.project.model.Admin;

public interface AdminService {
	
	Admin getAdminByUsername(String username);
	Admin getAdminById(long adminId);
	
	Admin createAdmin(Admin admin);
}
