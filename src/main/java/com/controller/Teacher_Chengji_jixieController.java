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

import com.pojo.ChengJi_jixie;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.Teacher_Chengji_jixieService;

@Controller
public class Teacher_Chengji_jixieController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_jixieService")
	private Teacher_Chengji_jixieService teacher_Chengji_jixieService;
	
	 int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_jixie")
	@ResponseBody
	public String addTeacher_Chengji_jixie(ChengJi_jixie jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_jixieService.addTeacher_Chengji_jixie(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_jixie")
	@ResponseBody
	public String modifyTeacher_Chengji_jixie(ChengJi_jixie jx) {
		int result=teacher_Chengji_jixieService.modifyTeacher_Chengji_jixie(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_jixie")
	@ResponseBody
	public String deleteTeacher_Chengji_jixie(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jixie_id : idStr) {
			teacher_Chengji_jixieService.deleteTeacher_Chengji_jixie(Integer.parseInt(jixie_id));
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_jixieList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_jixieList(@RequestParam("page") Integer page,HttpServletRequest req,
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
		model.put("total", teacher_Chengji_jixieService.queryTeacher_Chengji_jixieListTotal(params));
		model.put("rows", teacher_Chengji_jixieService.queryTeacher_Chengji_jixieList(params));
		return model;
	}
}
