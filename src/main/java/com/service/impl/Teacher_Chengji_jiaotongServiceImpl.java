package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.Teacher_Chengji_jiaotongMapper;
import com.pojo.ChengJi_jiaotong;
import com.service.Teacher_Chengji_jiaotongService;


@Service("teacher_Chengji_jiaotongService")
public class Teacher_Chengji_jiaotongServiceImpl implements Teacher_Chengji_jiaotongService{

	@Autowired
	private Teacher_Chengji_jiaotongMapper teacher_Chengji_jiaotongMapper;

	@Override
	public int modifyTeacher_Chengji_jiaotong(ChengJi_jiaotong dt) {
		return teacher_Chengji_jiaotongMapper.modifyTeacher_Chengji_jiaotong(dt);
	}

	@Override
	public int deleteTeacher_Chengji_jiaotong(int id) {
		return teacher_Chengji_jiaotongMapper.deleteTeacher_Chengji_jiaotong(id);
	}

	@Override
	public List<ChengJi_jiaotong> queryTeacher_Chengji_jiaotongList(Map<String, Object> params) {
		return teacher_Chengji_jiaotongMapper.queryTeacher_Chengji_jiaotongList(params);
	}

	@Override
	public Long queryTeacher_Chengji_jiaotongListTotal(Map<String, Object> params) {
		return teacher_Chengji_jiaotongMapper.queryTeacher_Chengji_jiaotongListTotal(params);
	}

	@Override
	public int addTeacher_Chengji_jiaotong(ChengJi_jiaotong jt) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jiaotongMapper.addTeacher_Chengji_jiaotong(jt);
	}

	@Override
	public int addTeacher_Chengji_jiaotongByExcel(List<Object> jiaotongList) {
		// TODO Auto-generated method stub
		return teacher_Chengji_jiaotongMapper.addTeacher_Chengji_jiaotongByExcel(jiaotongList);
	}


}
