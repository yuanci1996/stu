package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.ChengJi_jiaotong;

public interface Teacher_Chengji_jiaotongMapper {

	/**
	 * ������ͨ�ɼ�
	 * @param jt
	 * @return
	 */
	public int addTeacher_Chengji_jiaotong(ChengJi_jiaotong jt);
	
	/**
	 * �޸Ľ�ͨ�ɼ�
	 * @param jt
	 * @return
	 */
	public int modifyTeacher_Chengji_jiaotong(ChengJi_jiaotong jt);
	
	/**
	 * ɾ����ͨ�ɼ�
	 * @param id
	 * @return
	 */
	public int deleteTeacher_Chengji_jiaotong(int id);
	
	/**
	 * ��ѯ��ͨ�ɼ�
	 * @param params
	 * @return
	 */
	public List<ChengJi_jiaotong> queryTeacher_Chengji_jiaotongList(Map<String,Object> params);
	
	/**
	 * ��ѯ��ͨ�ɼ�������EasyUi���裩
	 * @param params
	 * @return
	 */
	public Long queryTeacher_Chengji_jiaotongListTotal(Map<String,Object> params);
}
