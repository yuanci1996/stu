package com.mapper;

import org.apache.ibatis.annotations.Param;

import com.pojo.AdminForm;

public interface AdminMapper {

	public AdminForm login(@Param("admin_account")String admin_account,@Param("admin_password") String admin_password);
	
}
