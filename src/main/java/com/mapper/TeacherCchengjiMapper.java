package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.CchengjiForm;
import com.pojo.StudentForm;

public interface TeacherCchengjiMapper {
	/**
	 * Ìí¼Ó×Û²â³É¼¨
	 * @param cchengjiForm
	 * @return
	 */
	public int addScore(CchengjiForm cchengjiForm);
	/**
	 * É¾³ý×Û²â³É¼¨
	 * @param student_number
	 */
	public void deleteCchengji(String cc_id);
	/**
	 * ÐÞ¸Ä×Û²â³É¼¨
	 * @param student
	 * @return
	 */
	public int modifyCchengji(CchengjiForm cchengjiForm);
	/**
	 * ²éÑ¯×Û²â³É¼¨
	 * @param params
	 * @return
	 */
	public List<CchengjiForm> queryCchengjiList(Map<String,Object> params);
	/**
	 * ¹¤¾ß
	 */
	public Long queryCchengjiListJcTotal(Map<String,Object> params);
}
