package com.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StudentMapper;
import com.pojo.CchengjiForm;
import com.pojo.ChengJi_jiaotong;
import com.pojo.ChengJi_jixie;
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
	
	@Override
	public Long queryStudentListjcTotal(Map<String, Object> params) {
		return studentMapper.queryStudentListTotal(params);
	}
	
	@Override
	public Long queryStudentListgbTotal(Map<String, Object> params) {
		return studentMapper.queryStudentListTotal(params);
	}
	@Override
	public void modifyStudentep(String student_number,String student_password) {
		 studentMapper.modifyStudentep(student_number, student_password);
	}
//	@Override
//	public CchengjiForm querygradeall(@Param("student_number")String student_number) {
//		return studentMapper.querygradeall(student_number);
//	}

	@Override
	public List<ChengJi_jixie> queryStuChengJi_jixie(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return studentMapper.queryStuChengJi_jixie(params);
	}

	@Override
	public Long queryStuChengJi_jixieTotal(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return studentMapper.queryStuChengJi_jixieTotal(params);
	}

	@Override
	public List<ChengJi_jiaotong> queryStuChengJi_jiaotong(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return studentMapper.queryStuChengJi_jiaotong(params);
	}

	@Override
	public Long queryStuChengJi_jiaotongTotal(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return studentMapper.queryStuChengJi_jiaotongTotal(params);
	}

}
