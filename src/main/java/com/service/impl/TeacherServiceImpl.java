package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TeacherMapper;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;
import com.pojo.TeacherForm;
import com.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public TeacherForm login(String teacher_number, String teacher_password) {
		return teacherMapper.login(teacher_number, teacher_password);
	}

	@Override
	public int addStudent(StudentForm student) {
		return teacherMapper.addStudent(student);
	}

	@Override
	public void deleteStudent(String student_number) {
		teacherMapper.deleteStudent(student_number);
	}

	@Override
	public int modifyStudent(StudentForm student) {
	return	teacherMapper.modifyStudent(student);
	}

	@Override
	public List<StudentForm> queryStudentList(Map<String, Object> params) {
		return teacherMapper.queryStudentList(params);
	}

	@Override
	public int addStudentJc(JianChengForm studentJc) {
	 	return teacherMapper.addStudentJc(studentJc);
	}

	@Override
	public void deleteStudentJc(String student_number) {
		teacherMapper.deleteStudentJc(student_number);
	}

	@Override
	public int modifyStudentJc(JianChengForm studentJc) {
		return teacherMapper.modifyStudentJc(studentJc);
	}

	@Override
	public List<JianChengForm> queryStudentListJc(Map<String, Object> params) {
		
		return teacherMapper.queryStudentListJc(params);
	}

	@Override
	public Long queryStudentListTotal(Map<String, Object> params) {
		return teacherMapper.queryStudentListTotal(params);
	}

	@Override
	public Long queryStudentListJcTotal(Map<String, Object> params) {
		return teacherMapper.queryStudentListJcTotal(params);
	}

	@Override
	public int addStudentByExcel(List<Object> stuList) {
		return teacherMapper.addStudentByExcel(stuList);
	}

}
