package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;






import com.pojo.CchengjiForm;
import com.pojo.ChengJi_jiaotong;
import com.pojo.ChengJi_jixie;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;

public interface StudentService {
	/**
	 * 登陆
	 * @param student_number
	 * @param admin_password
	 * @return
	 */
	public StudentForm login(@Param("student_number")String student_number,@Param("student_password") String admin_password);
	/**
	 * 查询学生信息
	 * @param student_number
	 * @return
	 */
	public List<StudentForm> queryall(Map<String,Object> params);
	/**
	 * 查询学生奖惩
	 * @param student_number
	 * @return
	 */
	public List<JianChengForm> queryjcall(Map<String,Object> params);
	/**
	 * 查询学生综测
	 * @param student_number
	 * @return
	 */
	public List<CchengjiForm> querygball(Map<String,Object> params);
	/**
	 * 
	 */
	public Long queryStudentListTotal(Map<String,Object> params);
	/**
	 * 
	 */
	public Long queryStudentListjcTotal(Map<String,Object> params);
	/**
	 * 
	 */
	public Long queryStudentListgbTotal(Map<String,Object> params);
	/**
	 * 机械学生查询成绩
	 */
	public List<ChengJi_jixie> queryStuChengJi_jixie(Map<String,Object> params);
	public Long queryStuChengJi_jixieTotal(Map<String,Object> params);
	
	/**
	 * 交通学生查询成绩
	 */
	public List<ChengJi_jiaotong> queryStuChengJi_jiaotong(Map<String,Object> params);
	public Long queryStuChengJi_jiaotongTotal(Map<String,Object> params);
}
