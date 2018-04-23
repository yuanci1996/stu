package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_kuaiji;

public interface Teacher_Chengji_kuaijiMapper {

	/**
	 * ������Ƴɼ�
	 * @param kj
	 * @return
	 */
	public int addTeacher_Chengji_kuaiji(ChengJi_kuaiji kj);
	
	/**
	 * �޸Ļ�Ƴɼ�
	 * @param kj
	 * @return
	 */
	public int modifyTeacher_Chengji_kuaiji(ChengJi_kuaiji kj);
	
	/**
	 * ɾ����Ƴɼ�
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_kuaiji(int id);
	
	/**
	 * ��ѯ��Ƴɼ�
	 * @param params
	 * @return
	 */
	public List<ChengJi_kuaiji> queryTeacher_Chengji_kuaijiList(Map<String,Object> params);
	
	/**
	 * ��ѯ��Ƴɼ�������EasyUi���裩
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_kuaijiListTotal(Map<String,Object> params);
}
