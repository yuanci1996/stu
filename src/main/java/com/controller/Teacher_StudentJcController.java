package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.JianChengForm;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.TeacherService;

@Controller
public class Teacher_StudentJcController {
	@Autowired
	@Qualifier(value="teacherService")
	private TeacherService teacherService;
    int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	/**
	 * 通过传入分页数据以及取得老师的班级id来取得有相同班级id的学生奖惩列表
	 * 通过学号查询时传入学号参数
	 * @param page
	 * @param rows
	 * @param student_number
	 * @return
	 */
	@RequestMapping(value="/te_adn_queryStudentJcList")
	@ResponseBody
	public ModelMap queryStudentListJc(@RequestParam("page") Integer page,HttpServletRequest req,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		HttpSession session=req.getSession();
		TeacherForm user=(TeacherForm) session.getAttribute("user");
		zhuanye_id=user.getZhuanye_id();
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		params.put("zhuanye_id", zhuanye_id);
		model.put("total", teacherService.queryStudentListJcTotal(params));
		model.put("rows", teacherService.queryStudentListJc(params));
		params.clear();
		return model;
	}
	
	/**
	 * 通过传入的id来删除学生奖惩
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/te_adn_deleteStudentJc")
	@ResponseBody
	public String deleteStudentJc(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jiangcheng_id : idStr) {
			teacherService.deleteStudentJc(jiangcheng_id);
		}
			return "true";
	}
	
	/**
	 * 通过传入的学生实体类来修改学生奖惩
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/te_adn_modifyStudentJc")
	@ResponseBody
	public String modifyStudentJc(JianChengForm s) {
		int result=teacherService.modifyStudentJc(s);		
		if(result>0) {
			return "true";
			}else {
				return "false";
			}	
	}
	
	/**
	 * 通过传入的学生奖惩参数以及获取本登录老师的班级id来增加学生奖惩
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/te_adn_addStudentJc")
	@ResponseBody
	public String addStudentJc(JianChengForm s) {
		s.setZhuanye_id(zhuanye_id);
		int result=teacherService.addStudentJc(s);
		if(result>0) {
			return "true";
			}else {
				return "false";
			}
	}
}
