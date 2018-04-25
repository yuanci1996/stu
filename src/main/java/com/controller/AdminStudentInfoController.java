package com.controller;


import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.pojo.PageBean;
import com.pojo.StudentForm;
import com.service.TeacherService;

@Controller
public class AdminStudentInfoController {
	@Autowired
	@Qualifier(value="teacherService")
	private TeacherService teacherService;
	
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
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number,
			@RequestParam(value = "zhuanye_id", required = false) String zhuanye_id) {
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		params.put("zhuanye_id", zhuanye_id);
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
//		s.setZhuanye_id(zhuanye_id);;//������ʦ�İ༶����ѧ���İ༶Id
		if(s.getStudent_zhuanye().equals("�������+��е���ӹ���")) {
			s.setZhuanye_id(1);
		}else if(s.getStudent_zhuanye().equals("�������+��ͨ���乤��")){
			s.setZhuanye_id(2);
		}
		s.setStudent_password("123456");//ѧ����ʼ����
		int result=teacherService.addStudent(s);
		if(result>0) {
			return "true";
			}else {
				return "false";
			}
	}
}

