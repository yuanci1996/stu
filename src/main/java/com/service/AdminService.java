package com.service;

import org.apache.ibatis.annotations.Param;

import com.pojo.AdminForm;

public interface AdminService {
	/*
	 *����
	 */
	public AdminForm login(@Param("admin_account")String admin_account,@Param("admin_password") String admin_password);
	/**
	 * �޸�����
	 * @param student_number
	 * @param student_password
	 */
	public void modifyAdminep(@Param("admin_account")String admin_account,@Param("admin_password")String admin_password);
	
}
