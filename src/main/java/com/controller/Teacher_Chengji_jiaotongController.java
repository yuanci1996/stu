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

import com.pojo.ChengJi_jiaotong;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.Teacher_Chengji_jiaotongService;

@Controller
public class Teacher_Chengji_jiaotongController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_jiaotongService")
	private Teacher_Chengji_jiaotongService teacher_Chengji_jiaotongService;
	
	 int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_jiaotong")
	@ResponseBody
	public String addTeacher_Chengji_jiaotong(ChengJi_jiaotong jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_jiaotongService.addTeacher_Chengji_jiaotong(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_jiaotong")
	@ResponseBody
	public String modifyTeacher_Chengji_jiaotong(ChengJi_jiaotong jx) {
		int result=teacher_Chengji_jiaotongService.modifyTeacher_Chengji_jiaotong(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_jiaotong")
	@ResponseBody
	public String deleteTeacher_Chengji_jiaotong(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jiaotong_id : idStr) {
			teacher_Chengji_jiaotongService.deleteTeacher_Chengji_jiaotong(Integer.parseInt(jiaotong_id));
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_jiaotongList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_jiaotongList(@RequestParam("page") Integer page,HttpServletRequest req,
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
		model.put("total", teacher_Chengji_jiaotongService.queryTeacher_Chengji_jiaotongListTotal(params));
		model.put("rows", teacher_Chengji_jiaotongService.queryTeacher_Chengji_jiaotongList(params));
		return model;
	}
}
