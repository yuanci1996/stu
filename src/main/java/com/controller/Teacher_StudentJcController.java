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

import com.pojo.JianChengForm;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.TeacherService;

@Controller
public class Teacher_StudentJcController {
	@Autowired
	@Qualifier(value="teacherService")
	private TeacherService teacherService;
    int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	/**
	 * ͨ�������ҳ�����Լ�ȡ����ʦ�İ༶id��ȡ������ͬ�༶id��ѧ�������б�
	 * ͨ��ѧ�Ų�ѯʱ����ѧ�Ų���
	 * @param page
	 * @param rows
	 * @param student_number
	 * @return
	 */
	@RequestMapping(value="/te_adn_queryStudentJcList")
	@ResponseBody
	public ModelMap queryStudentListJc(@RequestParam("page") Integer page,HttpServletRequest req,
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
		model.put("total", teacherService.queryStudentListJcTotal(params));
		model.put("rows", teacherService.queryStudentListJc(params));
		params.clear();
		return model;
	}
	
	/**
	 * ͨ�������id��ɾ��ѧ������
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/te_adn_deleteStudentJc")
	@ResponseBody
	public String deleteStudentJc(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jiangcheng_id : idStr) {
			teacherService.deleteStudentJc(jiangcheng_id);
		}
			return "true";
	}
	
	/**
	 * ͨ�������ѧ��ʵ�������޸�ѧ������
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/te_adn_modifyStudentJc")
	@ResponseBody
	public String modifyStudentJc(JianChengForm s) {
		int result=teacherService.modifyStudentJc(s);		
		if(result>0) {
			return "true";
			}else {
				return "false";
			}	
	}
	
	/**
	 * ͨ�������ѧ�����Ͳ����Լ���ȡ����¼��ʦ�İ༶id������ѧ������
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/te_adn_addStudentJc")
	@ResponseBody
	public String addStudentJc(JianChengForm s) {
		s.setZhuanye_id(zhuanye_id);
		int result=teacherService.addStudentJc(s);
		if(result>0) {
			return "true";
			}else {
				return "false";
			}
	}
}
