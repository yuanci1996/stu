package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.ChengJi_daotie;
import com.pojo.PageBean;
import com.service.Teacher_Chengji_daotieService;

@Controller
public class Teacher_Chengji_daotieController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_daotieService")
	private Teacher_Chengji_daotieService teacher_Chengji_daotieService;
	
	 int zhuanye_id=5;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_daotie")
	@ResponseBody
	public String addTeacher_Chengji_daotie(ChengJi_daotie jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_daotieService.addTeacher_Chengji_daotie(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_daotie")
	@ResponseBody
	public String modifyTeacher_Chengji_daotie(ChengJi_daotie jx) {
		int result=teacher_Chengji_daotieService.modifyTeacher_Chengji_daotie(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_daotie")
	@ResponseBody
	public String deleteTeacher_Chengji_daotie(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String daotie_id : idStr) {
			teacher_Chengji_daotieService.deleteTeacher_Chengji_daotie(Integer.parseInt(daotie_id));
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_daotieList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_daotieList(@RequestParam("page") Integer page,HttpServletRequest req,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		params.put("zhuanye_id", zhuanye_id);
		model.put("total", teacher_Chengji_daotieService.queryTeacher_Chengji_daotieListTotal(params));
		model.put("rows", teacher_Chengji_daotieService.queryTeacher_Chengji_daotieList(params));
		return model;
	}
}
