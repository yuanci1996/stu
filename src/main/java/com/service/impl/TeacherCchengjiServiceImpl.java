package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TeacherCchengjiMapper;
import com.mapper.TeacherMapper;
import com.pojo.CchengjiForm;
import com.service.TeacherCchengjiService;
@Service("teacherCchengjiService")
public class TeacherCchengjiServiceImpl implements TeacherCchengjiService {
	@Autowired
	private TeacherCchengjiMapper teacherCchengjiMapper;
	
	@Override
	public int addScore(CchengjiForm cchengjiForm){
		return teacherCchengjiMapper.addScore(cchengjiForm);
	}
	
	@Override
	public void deleteCchengji(String student_number){
		teacherCchengjiMapper.deleteCchengji(student_number);
	}
	
	@Override
	public int modifyCchengji(CchengjiForm cchengjiForm) {
		
		return teacherCchengjiMapper.modifyCchengji(cchengjiForm);
	}
	
	@Override
	public List<CchengjiForm> queryCchengjiList(Map<String,Object> params){
		return teacherCchengjiMapper.queryCchengjiList(params);
		
	}
	@Override
	public Long queryCchengjiListJcTotal(Map<String,Object> params) {
		return teacherCchengjiMapper.queryCchengjiListJcTotal(params);
	}
}
