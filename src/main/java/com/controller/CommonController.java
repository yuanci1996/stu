package com.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController {
	
	/**
	 * 给url附加参数
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{formName}")
	public String loginForm(@PathVariable String formName) throws Exception {
		return formName;
	}
	
	 /**
	  * 判断是否登录
	  * @param req
	  * @return all_login.jsp
	  */
	@RequestMapping("/reLogin")
	public String reLogin(HttpServletRequest req) {
		HttpSession session=req.getSession();
		Object user=session.getAttribute("user");
		if (null != session || null != user) {
			session.removeAttribute("user");
		}
		return "all_login";
	}


}
