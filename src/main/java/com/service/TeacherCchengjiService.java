package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.CchengjiForm;

public interface TeacherCchengjiService {
	/**
	 * ����۲�ɼ�
	 * @param cchengjiForm
	 * @return
	 */
	public int addScore(CchengjiForm cchengjiForm);
	/**
	 * ɾ���۲�ɼ�
	 * @param student_number
	 */
	public void deleteCchengji(String student_number);
	/**
	 * �޸��۲�ɼ�
	 * @param student
	 * @return
	 */
	public int modifyCchengji(CchengjiForm cchengjiForm);
	/**
	 * ��ѯ�۲�ɼ�
	 * @param params
	 * @return
	 */
	public List<CchengjiForm> queryCchengjiList(Map<String,Object> params);
	/**
	 * ����
	 * @param params
	 * @return
	 */
	public Long queryCchengjiListJcTotal(Map<String,Object> params);
	
}
