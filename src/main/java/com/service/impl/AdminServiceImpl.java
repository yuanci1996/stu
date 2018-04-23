package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.AdminMapper;
import com.pojo.AdminForm;
import com.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public AdminForm login(String admin_account, String admin_password) {
		return adminMapper.login(admin_account, admin_password);
	}
	
	
}
