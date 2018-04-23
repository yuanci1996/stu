package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pojo.AdminForm;
import com.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	@Qualifier(value="adminService")
	private AdminService adminService;


	@RequestMapping(value="/admin_login")
	public String admin_login(String admin_account,String admin_password,HttpServletRequest req, RedirectAttributes attr) {
		HttpSession session=req.getSession();
		AdminForm user=adminService.login(admin_account, admin_password);
		if(user!=null) {
			session.setAttribute("user", user);
			return "redirect:admin_main";	
		}else {
		attr.addFlashAttribute("Msg", "账号或密码错误！！");
		return "all_login";
		}
	}
	
}
