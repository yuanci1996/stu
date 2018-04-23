package com.service.impl;


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
	public StudentForm queryall(String student_number){
		return studentMapper.queryall(student_number);
	}
	
	@Override
	public JianChengForm queryjcall(String student_number){
		return studentMapper.queryjcall(student_number);
	}

	@Override
	public CchengjiForm querygball(String student_number){
		return studentMapper.querygball(student_number);
	}
}
