package com.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.AdminForm;
import com.pojo.CchengjiForm;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.TeacherCchengjiService;


@Controller
public class AdminCchengjiController {

	@Autowired
	@Qualifier(value="teacherCchengjiService")
	private TeacherCchengjiService teacherCchengjiService;
	
	int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addAdminCchengji")
	@ResponseBody
	public String addTeacher_Cchengji(CchengjiForm jx) {
		jx.setZhuanye_id(zhuanye_id);
		int F1 = Integer.parseInt(jx.getFosc());
		int F21 = Integer.parseInt(jx.getFtosc());
		int F22 = Integer.parseInt(jx.getFtwsc());
		int F3 = Integer.parseInt(jx.getFrsc());
		int F4 =Integer.parseInt(jx.getFfsc());
		Double F;
		F=(Double)(F4*0.7+0.3*((F1*0.6+F22-F21)*0.8+F3*0.2));
		DecimalFormat    df   = new DecimalFormat("######0.00");
		String str = ""+df.format(F);
		jx.setFsc(str);
		int result=	teacherCchengjiService.addScore(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyAdminCchengji")
	@ResponseBody
	public String modifyTeacher_Cchengji(CchengjiForm jx) {
		int F1 = Integer.parseInt(jx.getFosc());
		int F21 = Integer.parseInt(jx.getFtosc());
		int F22 = Integer.parseInt(jx.getFtwsc());
		int F3 = Integer.parseInt(jx.getFrsc());
		int F4 =Integer.parseInt(jx.getFfsc());
		Double F;
		F=(Double)(F4*0.7+0.3*((F1*0.6+F22-F21)*0.8+F3*0.2));
		DecimalFormat    df   = new DecimalFormat("######0.00");
		String str = ""+df.format(F);
		jx.setFsc(str);
		int result=teacherCchengjiService.modifyCchengji(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteAdminCchengji")
	@ResponseBody
	public String deleteTeacher_Cchengji(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jixie_id : idStr) {
			teacherCchengjiService.deleteCchengji(jixie_id);
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryAdminCchengjiList")
	@ResponseBody
	public ModelMap queryTeacher_CchengjiList(@RequestParam("page") Integer page,HttpServletRequest req,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		HttpSession session=req.getSession();
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		model.put("total", teacherCchengjiService.queryCchengjiListJcTotal(params));
		model.put("rows", teacherCchengjiService.queryCchengjiList(params));
		return model;
	}
}
