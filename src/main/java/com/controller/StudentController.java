package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pojo.AdminForm;
import com.pojo.CchengjiForm;
import com.pojo.JianChengForm;
import com.pojo.PageBean;
import com.pojo.StudentForm;
import com.service.StudentService;
import com.service.TeacherService;

@Controller
public class StudentController {
	@Autowired
	@Qualifier(value = "studentService")
	private StudentService studentService;

	private String Sid;
	HashMap<String, Object> params = new HashMap<String, Object>();

	// 学生登入
	@RequestMapping(value = "/student_login")
	public String admin_login(String student_number, String student_password, HttpServletRequest req,
			RedirectAttributes attr) {
		HttpSession session = req.getSession();
		StudentForm user = studentService.login(student_number, student_password);
		if (user != null) {
			session.setAttribute("user", user);
			Sid = user.getStudent_number();
			return "redirect:student_main";
		} else {
			attr.addFlashAttribute("Msg", "账号或密码错误！！");
			return "all_login";
		}
	}

	// 查询学生基本信息
	@RequestMapping(value = "/studentlist")
	@ResponseBody
	public ModelMap student_list(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows,
			@RequestParam(value = "student_number", required = false) String student_number) {
		ModelMap model = new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", Sid);
		model.put("total", studentService.queryStudentListTotal(params));
		model.put("rows", studentService.queryall(params));
		params.clear();
		return model;
	}

	// 查询学生奖惩信息
	@RequestMapping(value = "/studentjclist")
	@ResponseBody
	public ModelMap student_jclist(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows,
			@RequestParam(value = "student_number", required = false) String student_number) {
		// List<StudentForm> list=(List<StudentForm>) studentService.queryjcall(id);
		ModelMap model = new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", Sid);
		model.put("total", studentService.queryStudentListjcTotal(params));
		model.put("rows", studentService.queryjcall(params));
	
		params.clear();
		return model;
	}

	// 查询学生成绩信息
	@RequestMapping(value = "/studentgradelist")
	@ResponseBody
	public ModelMap student_gradelist(HttpServletRequest request, HttpServletResponse response) {
		// List<StudentForm> list=(List<StudentForm>) studentService.querygradeall(id);
		// SchengjiForm user=studentService.querygradeall(Sid);
		ModelMap model = new ModelMap();
		model.put("total", 1);
		// model.put("rows", user);
		return model;
	}

	// 查询学生综合测评信息
	@RequestMapping(value = "/studentgblist")
	@ResponseBody
	public ModelMap student_gblist(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows,
			@RequestParam(value = "student_number", required = false) String student_number) {
		ModelMap model = new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", Sid);
		model.put("total", studentService.queryStudentListgbTotal(params));
		model.put("rows", studentService.querygball(params));
		params.clear();
		return model;
	}
	
	//修改密码
	@RequestMapping(value = "/studentep")
	public String student_ep(@RequestParam("txtNewPass")String student_password,HttpServletRequest req, RedirectAttributes attr) {
		studentService.modifyStudentep(Sid, student_password);
		return "redirect:student_main";
	}
	
	
}