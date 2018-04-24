package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.ChengJi_jixie;

public interface Teacher_Chengji_jixieService {

	/**
	 * ������е�ɼ�
	 * @param jx
	 * @return
	 */
	public int addTeacher_Chengji_jixie(ChengJi_jixie jx);
	
	/**
	 * �޸Ļ�е�ɼ�
	 * @param jx
	 * @return
	 */
	public int modifyTeacher_Chengji_jixie(ChengJi_jixie jx);
	
	/**
	 * ɾ����е�ɼ�
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_jixie(int id);
	
	/**
	 * ��ѯ��е�ɼ�
	 * @param params
	 * @return
	 */
	public List<ChengJi_jixie> queryTeacher_Chengji_jixieList(Map<String,Object> params);
	
	/**
	 * ��ѯ��е�ɼ�������EasyUi���裩
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_jixieListTotal(Map<String,Object> params);
	
	/**
	 * �����е�ɼ�Excel
	 * @param jixieList
	 * @return
	 */
	public int addTeacher_Chengji_jixieByExcel(@Param("jixieList") List<Object> jixieList);
}
