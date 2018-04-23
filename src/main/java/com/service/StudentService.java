package com.service;

import org.apache.ibatis.annotations.Param;






import com.pojo.CchengjiForm;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;

public interface StudentService {
	public StudentForm login(@Param("student_number")String student_number,@Param("student_password") String admin_password);
	public StudentForm queryall(@Param("student_number")String student_number);
	public JianChengForm queryjcall(@Param("student_number")String student_number);
	public CchengjiForm querygball(@Param("student_number")String student_number);
}
