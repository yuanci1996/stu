package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.CchengjiForm;
import com.pojo.StudentForm;

public interface TeacherCchengjiMapper {
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
	public void deleteCchengji(String cc_id);
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
	 */
	public Long queryCchengjiListJcTotal(Map<String,Object> params);
}
