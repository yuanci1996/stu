package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.ChengJi_jiaotong;

public interface Teacher_Chengji_jiaotongService {

	/**
	 * 新增交通成绩
	 * @param jt
	 * @return
	 */
	public int addTeacher_Chengji_jiaotong(ChengJi_jiaotong jt);
	
	/**
	 * 修改交通成绩
	 * @param jt
	 * @return
	 */
	public int modifyTeacher_Chengji_jiaotong(ChengJi_jiaotong jt);
	
	/**
	 * 删除交通成绩
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_jiaotong(int id);
	
	/**
	 * 查询交通成绩
	 * @param params
	 * @return
	 */
	public List<ChengJi_jiaotong> queryTeacher_Chengji_jiaotongList(Map<String,Object> params);
	
	/**
	 * 查询交通成绩总数（EasyUi所需）
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_jiaotongListTotal(Map<String,Object> params);
	
	/**
	 * 导入交通成绩Excel
	 * @param jiaotongList
	 * @return
	 */
	public int addTeacher_Chengji_jiaotongByExcel(@Param("jiaotongList") List<Object> jiaotongList);
}
