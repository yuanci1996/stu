package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.CchengjiForm;

public interface TeacherCchengjiService {
	/**
	 * 添加综测成绩
	 * @param cchengjiForm
	 * @return
	 */
	public int addScore(CchengjiForm cchengjiForm);
	/**
	 * 删除综测成绩
	 * @param student_number
	 */
	public void deleteCchengji(String student_number);
	/**
	 * 修改综测成绩
	 * @param student
	 * @return
	 */
	public int modifyCchengji(CchengjiForm cchengjiForm);
	/**
	 * 查询综测成绩
	 * @param params
	 * @return
	 */
	public List<CchengjiForm> queryCchengjiList(Map<String,Object> params);
	/**
	 * 工具
	 * @param params
	 * @return
	 */
	public Long queryCchengjiListJcTotal(Map<String,Object> params);
	
    /**
     * 导入学生Excel
     * @param stuList
     * @return
     */
    public int addStudentCcjByExcel(@Param("ccjList") List<Object> ccjList); 
}
