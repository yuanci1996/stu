package com.mapper;


import org.apache.ibatis.annotations.Param;

import com.pojo.CchengjiForm;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;

public interface StudentMapper {
	public StudentForm login(@Param("student_number")String student_number,@Param("student_password") String student_password);
	public StudentForm queryall(@Param("student_number")String student_number);
	public JianChengForm queryjcall(@Param("student_number")String student_number);
	public CchengjiForm querygball(@Param("student_number")String student_number);
}
