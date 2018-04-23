 package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pojo.CchengjiForm;
import com.pojo.JianChengForm;
import com.pojo.StudentForm;
import com.service.StudentService;


@Controller
public class StudentController {
	@Autowired
	@Qualifier(value="studentService")
	private StudentService studentService;
	private String Sid;
//学生登入
	@RequestMapping(value="/student_login")
	public String admin_login(String student_number,String student_password,HttpServletRequest req, RedirectAttributes attr) {
		HttpSession session=req.getSession();
		StudentForm user=studentService.login(student_number, student_password);	
		if(user!=null) {
			session.setAttribute("user", user);
			Sid = user.getStudent_number();
			return "redirect:student_main";	
		}else {
			attr.addFlashAttribute("Msg", "账号或密码错误！！");
			return "all_login";
		}
	}
//查询学生基本信息
	@RequestMapping(value="/studentlist")
	@ResponseBody
	public ModelMap student_list(HttpServletRequest request,HttpServletResponse response){
		StudentForm user=studentService.queryall(Sid);
		ModelMap model=new ModelMap();
		model.put("total", 1);
		model.put("rows", user);
        return model;
	}
	
//查询学生奖惩信息
	@RequestMapping(value="/studentjclist")
	@ResponseBody
	public ModelMap student_jclist(HttpServletRequest request,HttpServletResponse response){
		//List<StudentForm> list=(List<StudentForm>) studentService.queryjcall(id);
		JianChengForm user=studentService.queryjcall(Sid);
		ModelMap model=new ModelMap();
		model.put("total", 1);
		model.put("rows", user);
		return model;
	}
	
//查询学生成绩信息
	@RequestMapping(value="/studentgradelist")
	@ResponseBody
	public ModelMap student_gradelist(HttpServletRequest request,HttpServletResponse response){
		//List<StudentForm> list=(List<StudentForm>) studentService.querygradeall(id);
//		SchengjiForm user=studentService.querygradeall(Sid);
		ModelMap model=new ModelMap();
		model.put("total", 1);
//		model.put("rows", user);
		return model;
	}
	
//查询学生综合测评信息
	@RequestMapping(value="/studentgblist")
	@ResponseBody
	public ModelMap student_gblist(HttpServletRequest request,HttpServletResponse response){
		//List<StudentForm> list=(List<StudentForm>) studentService.querygball(id);
		CchengjiForm user=studentService.querygball(Sid);
		ModelMap model=new ModelMap();
		model.put("total", 1);
		model.put("rows", user);
		return model;
	}
}