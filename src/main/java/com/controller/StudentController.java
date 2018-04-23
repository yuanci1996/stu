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
//ѧ������
	@RequestMapping(value="/student_login")
	public String admin_login(String student_number,String student_password,HttpServletRequest req, RedirectAttributes attr) {
		HttpSession session=req.getSession();
		StudentForm user=studentService.login(student_number, student_password);	
		if(user!=null) {
			session.setAttribute("user", user);
			Sid = user.getStudent_number();
			return "redirect:student_main";	
		}else {
			attr.addFlashAttribute("Msg", "�˺Ż�������󣡣�");
			return "all_login";
		}
	}
//��ѯѧ��������Ϣ
	@RequestMapping(value="/studentlist")
	@ResponseBody
	public ModelMap student_list(HttpServletRequest request,HttpServletResponse response){
		StudentForm user=studentService.queryall(Sid);
		ModelMap model=new ModelMap();
		model.put("total", 1);
		model.put("rows", user);
        return model;
	}
	
//��ѯѧ��������Ϣ
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
	
//��ѯѧ���ɼ���Ϣ
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
	
//��ѯѧ���ۺϲ�����Ϣ
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