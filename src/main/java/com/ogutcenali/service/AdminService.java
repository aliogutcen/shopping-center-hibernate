package com.ogutcenali.service;

import com.ogutcenali.dao.AdminDao;
import com.ogutcenali.entity.Admin;

public class AdminService {

	private AdminDao adminDao = new AdminDao();

	public void createAdmin(String fname, String lname, String pass, String email, String type) {

		String emailTogether = email + type;

		Admin admin = new Admin(fname, lname, emailTogether, pass);
		adminDao.save(admin);

	}

	public boolean checkAdminLogin(String email, String tf) {
		
		Admin admin = adminDao.listAll()
				.stream()
				.filter((a)-> a.getEmail().equals(email) && a.getPassword().equals(tf))
				.findAny()
				.orElse(null);
		
		if(admin!=null) {
			return true;
		}
		else 
			return false;
		
		
	}

}
