package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_daotie;

public interface Teacher_Chengji_daotieMapper {

	/**
	 * 新增道铁成绩
	 * @param dt
	 * @return
	 */
	public int addTeacher_Chengji_daotie(ChengJi_daotie dt);
	
	/**
	 * 修改道铁成绩
	 * @param dt
	 * @return
	 */
	public int modifyTeacher_Chengji_daotie(ChengJi_daotie dt);
	
	/**
	 * 删除道铁成绩
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_daotie(int id);
	
	/**
	 * 查询道铁成绩
	 * @param params
	 * @return
	 */
	public List<ChengJi_daotie> queryTeacher_Chengji_daotieList(Map<String,Object> params);
	
	/**
	 * 查询道铁成绩总数（EasyUi所需）
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_daotieListTotal(Map<String,Object> params);
}
