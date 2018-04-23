package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.Teacher_Chengji_kuaijiMapper;
import com.pojo.ChengJi_kuaiji;
import com.service.Teacher_Chengji_kuaijiService;

@Service("teacher_Chengji_kuaijiService")
public class Teacher_Chengji_kuaijiServiceImpl implements Teacher_Chengji_kuaijiService{

	@Autowired
	private Teacher_Chengji_kuaijiMapper teacher_Chengji_kuaijiMapper;

	@Override
	public int addTeacher_Chengji_kuaiji(ChengJi_kuaiji jx) {
		// TODO Auto-generated method stub
		return teacher_Chengji_kuaijiMapper.addTeacher_Chengji_kuaiji(jx);
	}

	@Override
	public int modifyTeacher_Chengji_kuaiji(ChengJi_kuaiji jx) {
		// TODO Auto-generated method stub
		return teacher_Chengji_kuaijiMapper.modifyTeacher_Chengji_kuaiji(jx);
	}

	@Override
	public int deleteTeacher_Chengji_kuaiji(int id) {
		// TODO Auto-generated method stub
		return teacher_Chengji_kuaijiMapper.deleteTeacher_Chengji_kuaiji(id);
	}

	@Override
	public List<ChengJi_kuaiji> queryTeacher_Chengji_kuaijiList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return teacher_Chengji_kuaijiMapper.queryTeacher_Chengji_kuaijiList(params);
	}

	@Override
	public Long queryTeacher_Chengji_kuaijiListTotal(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return teacher_Chengji_kuaijiMapper.queryTeacher_Chengji_kuaijiListTotal(params);
	}
}
