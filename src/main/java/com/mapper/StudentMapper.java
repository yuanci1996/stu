package com.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.CchengjiForm;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;

public interface StudentMapper {
	/**
	 * ����
	 * @param student_number
	 * @param student_password
	 * @return
	 */
	public StudentForm login(@Param("student_number")String student_number,@Param("student_password") String student_password);
	/**
	 * ��ѯ������Ϣ
	 * @param student_number
	 * @return
	 */
	public List<StudentForm> queryall(Map<String,Object> params);
	/**
	 * ��ѯ���˽���
	 * @param student_number
	 * @return
	 */
	public List<JianChengForm> queryjcall(Map<String,Object> params);
	/**
	 * ��ѯ�����ۺϲ���
	 * @param student_number
	 * @return
	 */
	public List<CchengjiForm> querygball(Map<String,Object> params);
	/**
	 * ����
	 */
	public Long queryStudentListTotal(Map<String,Object> params);
	/**
	 * �ɼ�
	 */

}
