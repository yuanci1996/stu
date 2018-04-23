package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_guidao;

public interface Teacher_Chengji_guidaoMapper {

	/**
	 * ��������ɼ�
	 * @param gd
	 * @return
	 */
	public int addTeacher_Chengji_guidao(ChengJi_guidao gd);
	
	/**
	 * �޸Ĺ���ɼ�
	 * @param gd
	 * @return
	 */
	public int modifyTeacher_Chengji_guidao(ChengJi_guidao gd);
	
	/**
	 * ɾ������ɼ�
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_guidao(int id);
	
	/**
	 * ��ѯ����ɼ�
	 * @param params
	 * @return
	 */
	public List<ChengJi_guidao> queryTeacher_Chengji_guidaoList(Map<String,Object> params);
	
	/**
	 * ��ѯ����ɼ�������EasyUi���裩
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_guidaoListTotal(Map<String,Object> params);
}
