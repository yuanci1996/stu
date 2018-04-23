package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_daotie;

public interface Teacher_Chengji_daotieMapper {

	/**
	 * ���������ɼ�
	 * @param dt
	 * @return
	 */
	public int addTeacher_Chengji_daotie(ChengJi_daotie dt);
	
	/**
	 * �޸ĵ����ɼ�
	 * @param dt
	 * @return
	 */
	public int modifyTeacher_Chengji_daotie(ChengJi_daotie dt);
	
	/**
	 * ɾ�������ɼ�
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_daotie(int id);
	
	/**
	 * ��ѯ�����ɼ�
	 * @param params
	 * @return
	 */
	public List<ChengJi_daotie> queryTeacher_Chengji_daotieList(Map<String,Object> params);
	
	/**
	 * ��ѯ�����ɼ�������EasyUi���裩
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_daotieListTotal(Map<String,Object> params);
}
