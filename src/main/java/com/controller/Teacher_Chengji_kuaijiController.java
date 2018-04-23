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

import com.pojo.ChengJi_kuaiji;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.Teacher_Chengji_kuaijiService;

@Controller
public class Teacher_Chengji_kuaijiController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_kuaijiService")
	private Teacher_Chengji_kuaijiService teacher_Chengji_kuaijiService;
	
	 int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_kuaiji")
	@ResponseBody
	public String addTeacher_Chengji_kuaiji(ChengJi_kuaiji jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_kuaijiService.addTeacher_Chengji_kuaiji(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_kuaiji")
	@ResponseBody
	public String modifyTeacher_Chengji_kuaiji(ChengJi_kuaiji jx) {
		int result=teacher_Chengji_kuaijiService.modifyTeacher_Chengji_kuaiji(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_kuaiji")
	@ResponseBody
	public String deleteTeacher_Chengji_kuaiji(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String kuaiji_id : idStr) {
			teacher_Chengji_kuaijiService.deleteTeacher_Chengji_kuaiji(Integer.parseInt(kuaiji_id));
		}
		return "true";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_kuaijiList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_kuaijiList(@RequestParam("page") Integer page,HttpServletRequest req,
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
		model.put("total", teacher_Chengji_kuaijiService.queryTeacher_Chengji_kuaijiListTotal(params));
		model.put("rows", teacher_Chengji_kuaijiService.queryTeacher_Chengji_kuaijiList(params));
		return model;
	}
}
