package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_kuaiji;

public interface Teacher_Chengji_kuaijiMapper {

	/**
	 * 新增会计成绩
	 * @param kj
	 * @return
	 */
	public int addTeacher_Chengji_kuaiji(ChengJi_kuaiji kj);
	
	/**
	 * 修改会计成绩
	 * @param kj
	 * @return
	 */
	public int modifyTeacher_Chengji_kuaiji(ChengJi_kuaiji kj);
	
	/**
	 * 删除会计成绩
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_kuaiji(int id);
	
	/**
	 * 查询会计成绩
	 * @param params
	 * @return
	 */
	public List<ChengJi_kuaiji> queryTeacher_Chengji_kuaijiList(Map<String,Object> params);
	
	/**
	 * 查询会计成绩总数（EasyUi所需）
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_kuaijiListTotal(Map<String,Object> params);
}
