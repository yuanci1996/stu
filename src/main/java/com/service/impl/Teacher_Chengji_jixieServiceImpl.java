package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.Teacher_Chengji_jixieMapper;
import com.pojo.ChengJi_jixie;
import com.service.Teacher_Chengji_jixieService;

@Service("teacher_Chengji_jixieService")
public class Teacher_Chengji_jixieServiceImpl implements Teacher_Chengji_jixieService{

	@Autowired
	private Teacher_Chengji_jixieMapper teacher_Chengji_jixieMapper;

	@Override
	public int addTeacher_Chengji_jixie(ChengJi_jixie jx) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jixieMapper.addTeacher_Chengji_jixie(jx);
	}

	@Override
	public int modifyTeacher_Chengji_jixie(ChengJi_jixie jx) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jixieMapper.modifyTeacher_Chengji_jixie(jx);
	}

	@Override
	public int deleteTeacher_Chengji_jixie(int id) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jixieMapper.deleteTeacher_Chengji_jixie(id);
	}

	@Override
	public List<ChengJi_jixie> queryTeacher_Chengji_jixieList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jixieMapper.queryTeacher_Chengji_jixieList(params);
	}

	@Override
	public Long queryTeacher_Chengji_jixieListTotal(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jixieMapper.queryTeacher_Chengji_jixieListTotal(params);
	}

	@Override
	public int addTeacher_Chengji_jixieByExcel(List<Object> jixieList) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jixieMapper.addTeacher_Chengji_jixieByExcel(jixieList);
	}
}
