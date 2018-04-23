package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_guidao;

public interface Teacher_Chengji_guidaoMapper {

	/**
	 * 新增轨道成绩
	 * @param gd
	 * @return
	 */
	public int addTeacher_Chengji_guidao(ChengJi_guidao gd);
	
	/**
	 * 修改轨道成绩
	 * @param gd
	 * @return
	 */
	public int modifyTeacher_Chengji_guidao(ChengJi_guidao gd);
	
	/**
	 * 删除轨道成绩
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_guidao(int id);
	
	/**
	 * 查询轨道成绩
	 * @param params
	 * @return
	 */
	public List<ChengJi_guidao> queryTeacher_Chengji_guidaoList(Map<String,Object> params);
	
	/**
	 * 查询轨道成绩总数（EasyUi所需）
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_guidaoListTotal(Map<String,Object> params);
}
