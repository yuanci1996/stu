package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.JianChengForm;
import com.pojo.StudentForm;
import com.pojo.TeacherForm;

public interface TeacherMapper {
	
	/**
	 * 老师登录
	 * @param teacher_number
	 * @param teacher_password
	 * @return
	 */
	public TeacherForm login(@Param("teacher_number")String teacher_number,@Param("teacher_password") String teacher_password);
	/**
	 * 修改密码
	 * @param student_number
	 * @param student_password
	 */
	public void modifyTeacherep(@Param("teacher_number")String teacher_number,@Param("teacher_password")String teacher_password);
	
	/**
	 * 新增学生
	 * @param student
	 */
	public int addStudent(StudentForm student);
	
	/**
	 * 通过学号删除学生
	 * @param student_number
	 */
	public void deleteStudent(String student_number);
	
	/**
	 * 修改学生信息
	 * @param student
	 */
	public int modifyStudent(StudentForm student);
	
	/**
	 * 查询学生列表
	 * @return
	 */
	public List<StudentForm> queryStudentList(Map<String,Object> params);
	public Long queryStudentListTotal(Map<String,Object> params);
	
    /**
     * 导入学生Excel
     * @param stuList
     * @return
     */
    public int addStudentByExcel(@Param("stuList") List<Object> stuList); 
	/**
	 * 新增学生奖惩
	 * @param student
	 */
	public int addStudentJc(JianChengForm studentJc);
	
	/**
	 * 通过学号删除学生奖惩
	 * @param student_number
	 */
	public void deleteStudentJc(String student_number);
	
	/**
	 * 修改学生奖惩
	 * @param student
	 */
	public int modifyStudentJc(JianChengForm studentJc);
	
	/**
	 * 查询学生奖惩
	 * @return
	 */
	public List<JianChengForm> queryStudentListJc(Map<String,Object> params);
	public Long queryStudentListJcTotal(Map<String,Object> params);
	
}
