package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.CchengjiForm;

public interface TeacherCchengjiService {
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
	public void deleteCchengji(String student_number);
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
	 * @param params
	 * @return
	 */
	public Long queryCchengjiListJcTotal(Map<String,Object> params);
	
}
