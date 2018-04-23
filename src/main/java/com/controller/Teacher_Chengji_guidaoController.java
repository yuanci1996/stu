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

import com.pojo.ChengJi_guidao;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.Teacher_Chengji_guidaoService;

@Controller
public class Teacher_Chengji_guidaoController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_guidaoService")
	private Teacher_Chengji_guidaoService teacher_Chengji_guidaoService;
	
	 int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_guidao")
	@ResponseBody
	public String addTeacher_Chengji_guidao(ChengJi_guidao jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_guidaoService.addTeacher_Chengji_guidao(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_guidao")
	@ResponseBody
	public String modifyTeacher_Chengji_guidao(ChengJi_guidao jx) {
		int result=teacher_Chengji_guidaoService.modifyTeacher_Chengji_guidao(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_guidao")
	@ResponseBody
	public String deleteTeacher_Chengji_guidao(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String guidao_id : idStr) {
			teacher_Chengji_guidaoService.deleteTeacher_Chengji_guidao(Integer.parseInt(guidao_id));
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_guidaoList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_guidaoList(@RequestParam("page") Integer page,HttpServletRequest req,
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
		model.put("total", teacher_Chengji_guidaoService.queryTeacher_Chengji_guidaoListTotal(params));
		model.put("rows", teacher_Chengji_guidaoService.queryTeacher_Chengji_guidaoList(params));
		return model;
	}
}
