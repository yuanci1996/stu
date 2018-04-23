package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.Teacher_Chengji_daotieMapper;
import com.pojo.ChengJi_daotie;
import com.service.Teacher_Chengji_daotieService;


@Service("teacher_Chengji_daotieService")
public class Teacher_Chengji_daotieServiceImpl implements Teacher_Chengji_daotieService{

	@Autowired
	private Teacher_Chengji_daotieMapper teacher_Chengji_daotieMapper;
	@Override
	public int addTeacher_Chengji_daotie(ChengJi_daotie dt) {
		return teacher_Chengji_daotieMapper.addTeacher_Chengji_daotie(dt);
	}

	@Override
	public int modifyTeacher_Chengji_daotie(ChengJi_daotie dt) {
		return teacher_Chengji_daotieMapper.modifyTeacher_Chengji_daotie(dt);
	}

	@Override
	public int deleteTeacher_Chengji_daotie(int id) {
		return teacher_Chengji_daotieMapper.deleteTeacher_Chengji_daotie(id);
	}

	@Override
	public List<ChengJi_daotie> queryTeacher_Chengji_daotieList(Map<String, Object> params) {
		return teacher_Chengji_daotieMapper.queryTeacher_Chengji_daotieList(params);
	}

	@Override
	public Long queryTeacher_Chengji_daotieListTotal(Map<String, Object> params) {
		return teacher_Chengji_daotieMapper.queryTeacher_Chengji_daotieListTotal(params);
	}

	
}
