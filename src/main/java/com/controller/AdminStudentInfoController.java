package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.pojo.PageBean;
import com.pojo.StudentForm;
import com.pojo.TeacherForm;
import com.service.TeacherService;
import com.utils.ExcelUtil;

@Controller
public class AdminStudentInfoController {
	@Autowired
	@Qualifier(value="teacherService")
	private TeacherService teacherService;
	
    int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();

	
	/**
	 * ͨ�������ҳ�����Լ�ȡ����ʦ�İ༶id��ȡ������ͬ�༶id��ѧ���б�
	 * ͨ��ѧ�Ų�ѯʱ����ѧ�Ų���
	 * @param page
	 * @param rows
	 * @param student_number
	 * @return
	 */
	@RequestMapping(value="/adminqueryStudentList")
	@ResponseBody
	public ModelMap queryStudentList(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		model.put("total", teacherService.queryStudentListTotal(params));
		model.put("rows", teacherService.queryStudentList(params));
		params.clear();
		return model;
	}
	
	/**
	 * ͨ�������ѧ����ɾ��ѧ����Ϣ
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/admindeleteStudent")
	@ResponseBody
	public String deleteStudent(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String student_number : idStr) {
			teacherService.deleteStudent(student_number);
		}
			return "true";
	}
	
	/**
	 * ͨ�������ѧ��ʵ�������޸�ѧ������
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/adminmodifyStudent")
	@ResponseBody
	public String modifyStudent(StudentForm s) {
		int result= teacherService.modifyStudent(s);
		if(result>0) {
		return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * ͨ�������ѧ�������Լ���ȡ����¼��ʦ�İ༶id������ѧ��
	 * ͬʱ����ѧ���ĳ�ʼ����
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/adminaddStudent")
	@ResponseBody
	public String addStudent(StudentForm s) {
		s.setZhuanye_id(zhuanye_id);;//������ʦ�İ༶����ѧ���İ༶Id
		s.setStudent_password("123456");//ѧ����ʼ����
		int result=teacherService.addStudent(s);
		if(result>0) {
			return "true";
			}else {
				return "false";
			}
	}
}

