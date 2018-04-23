package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.Teacher_Chengji_guidaoMapper;
import com.pojo.ChengJi_guidao;
import com.service.Teacher_Chengji_guidaoService;


@Service("teacher_Chengji_guidaoService")
public class Teacher_Chengji_guidaoServiceImpl implements Teacher_Chengji_guidaoService{

	@Autowired
	private Teacher_Chengji_guidaoMapper teacher_Chengji_guidaoMapper;

	@Override
	public int modifyTeacher_Chengji_guidao(ChengJi_guidao dt) {
		return teacher_Chengji_guidaoMapper.modifyTeacher_Chengji_guidao(dt);
	}

	@Override
	public int deleteTeacher_Chengji_guidao(int id) {
		return teacher_Chengji_guidaoMapper.deleteTeacher_Chengji_guidao(id);
	}

	@Override
	public List<ChengJi_guidao> queryTeacher_Chengji_guidaoList(Map<String, Object> params) {
		return teacher_Chengji_guidaoMapper.queryTeacher_Chengji_guidaoList(params);
	}

	@Override
	public Long queryTeacher_Chengji_guidaoListTotal(Map<String, Object> params) {
		return teacher_Chengji_guidaoMapper.queryTeacher_Chengji_guidaoListTotal(params);
	}

	@Override
	public int addTeacher_Chengji_guidao(ChengJi_guidao gd) {
		// TODO Auto-generated method stub
		return teacher_Chengji_guidaoMapper.addTeacher_Chengji_guidao(gd);
	}

	
}
