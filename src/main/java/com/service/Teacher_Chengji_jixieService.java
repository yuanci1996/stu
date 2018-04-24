package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.ChengJi_jixie;

public interface Teacher_Chengji_jixieService {

	/**
	 * 新增机械成绩
	 * @param jx
	 * @return
	 */
	public int addTeacher_Chengji_jixie(ChengJi_jixie jx);
	
	/**
	 * 修改机械成绩
	 * @param jx
	 * @return
	 */
	public int modifyTeacher_Chengji_jixie(ChengJi_jixie jx);
	
	/**
	 * 删除机械成绩
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_jixie(int id);
	
	/**
	 * 查询机械成绩
	 * @param params
	 * @return
	 */
	public List<ChengJi_jixie> queryTeacher_Chengji_jixieList(Map<String,Object> params);
	
	/**
	 * 查询机械成绩总数（EasyUi所需）
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_jixieListTotal(Map<String,Object> params);
	
	/**
	 * 导入机械成绩Excel
	 * @param jixieList
	 * @return
	 */
	public int addTeacher_Chengji_jixieByExcel(@Param("jixieList") List<Object> jixieList);
}
