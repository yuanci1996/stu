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
	 * 通过传入分页数据以及取得老师的班级id来取得有相同班级id的学生列表
	 * 通过学号查询时传入学号参数
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
	 * 通过传入的学号来删除学生信息
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
	 * 通过传入的学生实体类来修改学生参数
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
	 * 通过传入的学生参数以及获取本登录老师的班级id来增加学生
	 * 同时设置学生的初始密码
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/adminaddStudent")
	@ResponseBody
	public String addStudent(StudentForm s) {
//		s.setZhuanye_id(zhuanye_id);;//所属老师的班级放在学生的班级Id
		if(s.getStudent_zhuanye().equals("软件工程+机械电子工程")) {
			s.setZhuanye_id(1);
		}else if(s.getStudent_zhuanye().equals("软件工程+交通运输工程")){
			s.setZhuanye_id(2);
		}
		s.setStudent_password("123456");//学生初始密码
		int result=teacherService.addStudent(s);
		if(result>0) {
			return "true";
			}else {
				return "false";
			}
	}
}

