package com.mapper;

import org.apache.ibatis.annotations.Param;

import com.pojo.AdminForm;

public interface AdminMapper {
	/**
	 * µÇÈë
	 * @param admin_account
	 * @param admin_password
	 * @return
	 */
	public AdminForm login(@Param("admin_account")String admin_account,@Param("admin_password") String admin_password);
	/**
	 * ĞŞ¸ÄÃÜÂë
	 * @param student_number
	 * @param student_password
	 */
	public void modifyAdminep(@Param("admin_account")String admin_account,@Param("admin_password")String admin_password);
}
