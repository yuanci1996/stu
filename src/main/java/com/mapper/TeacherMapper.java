package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.JianChengForm;
import com.pojo.StudentForm;
import com.pojo.TeacherForm;

public interface TeacherMapper {
	
	/**
	 * ��ʦ��¼
	 * @param teacher_number
	 * @param teacher_password
	 * @return
	 */
	public TeacherForm login(@Param("teacher_number")String teacher_number,@Param("teacher_password") String teacher_password);
	/**
	 * �޸�����
	 * @param student_number
	 * @param student_password
	 */
	public void modifyTeacherep(@Param("teacher_number")String teacher_number,@Param("teacher_password")String teacher_password);
	
	/**
	 * ����ѧ��
	 * @param student
	 */
	public int addStudent(StudentForm student);
	
	/**
	 * ͨ��ѧ��ɾ��ѧ��
	 * @param student_number
	 */
	public void deleteStudent(String student_number);
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param student
	 */
	public int modifyStudent(StudentForm student);
	
	/**
	 * ��ѯѧ���б�
	 * @return
	 */
	public List<StudentForm> queryStudentList(Map<String,Object> params);
	public Long queryStudentListTotal(Map<String,Object> params);
	
    /**
     * ����ѧ��Excel
     * @param stuList
     * @return
     */
    public int addStudentByExcel(@Param("stuList") List<Object> stuList); 
	/**
	 * ����ѧ������
	 * @param student
	 */
	public int addStudentJc(JianChengForm studentJc);
	
	/**
	 * ͨ��ѧ��ɾ��ѧ������
	 * @param student_number
	 */
	public void deleteStudentJc(String student_number);
	
	/**
	 * �޸�ѧ������
	 * @param student
	 */
	public int modifyStudentJc(JianChengForm studentJc);
	
	/**
	 * ��ѯѧ������
	 * @return
	 */
	public List<JianChengForm> queryStudentListJc(Map<String,Object> params);
	public Long queryStudentListJcTotal(Map<String,Object> params);
	
}
