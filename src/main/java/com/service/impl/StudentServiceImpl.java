package com.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StudentMapper;
import com.pojo.CchengjiForm;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;
import com.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public StudentForm login(String student_number, String student_password) {
		return studentMapper.login(student_number, student_password);
	}
	
	@Override
	public List<StudentForm> queryall(Map<String,Object> params){
		return studentMapper.queryall(params);
	}
	
	@Override
	public List<JianChengForm> queryjcall(Map<String,Object> params){
		return studentMapper.queryjcall(params);
	}

	@Override
	public List<CchengjiForm> querygball(Map<String,Object> params){
		return studentMapper.querygball(params);
	}  
	
	@Override
	public Long queryStudentListTotal(Map<String, Object> params) {
		return studentMapper.queryStudentListTotal(params);
	}
	
//	@Override
//	public CchengjiForm querygradeall(@Param("student_number")String student_number) {
//		return studentMapper.querygradeall(student_number);
//	}

}
