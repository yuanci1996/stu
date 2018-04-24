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
public class TeacherController {
	@Autowired
	@Qualifier(value="teacherService")
	private TeacherService teacherService;
	
    int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	/**
	 * 教师登录
	 * @param teacher_number
	 * @param teacher_password
	 * @param req
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="/teacher_login")
	public String admin_login(String teacher_number,String teacher_password,HttpServletRequest req, RedirectAttributes attr) {
		HttpSession session=req.getSession();
		TeacherForm user=teacherService.login(teacher_number, teacher_password);
		if(user!=null) {
			session.setAttribute("user", user);
			zhuanye_id=user.getZhuanye_id();
			return "redirect:teacher_main";	
		}else {
		attr.addFlashAttribute("Msg", "账号或密码错误！！");
		return "all_login";
		}
	}
	
	/**
	 * 通过传入分页数据以及取得老师的班级id来取得有相同班级id的学生列表
	 * 通过学号查询时传入学号参数
	 * @param page
	 * @param rows
	 * @param student_number
	 * @return
	 */
	@RequestMapping(value="/te_adn_queryStudentList")
	@ResponseBody
	public ModelMap queryStudentList(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
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
	@RequestMapping(value="/te_adn_deleteStudent")
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
	@RequestMapping(value="/te_adn_modifyStudent")
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
	@RequestMapping(value="/te_adn_addStudent")
	@ResponseBody
	public String addStudent(StudentForm s) {
		s.setZhuanye_id(zhuanye_id);;//所属老师的班级放在学生的班级Id
		s.setStudent_password("123456");//学生初始密码
		int result=teacherService.addStudent(s);
		if(result>0) {
			return "true";
			}else {
				return "false";
			}
	}

	    /**
	     * 导入学生Excel
	     * 其他导入注意修改字段，路径，实体类
	     * @param file
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/importStuListByExcel")
	    @ResponseBody
	    public String importStuListByExcel(MultipartFile file,HttpServletRequest request) throws Exception{
	        String path = request.getSession().getServletContext().getRealPath("upload/importExcel/")+"/";
	        String fileName = file.getOriginalFilename();
	        String ExcelPath= path + fileName;
	        File dir = new File(path,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        file.transferTo(dir);
	        String keyValue ="学号:student_number,姓名:student_name,性别:student_sex,出生年月:student_bir,籍贯:student_jiguan,"
	        		+ "政治面貌:student_mianmao,民族:student_mingzu,学院:student_xueyuan,年级:student_nianji,专业:student_zhuanye,班级:class_id,学生类别:student_student_type,联系方式:student_tel,"
	        		+ "专业ID:zhuanye_id,辅导员:student_fudaoyuan";
	        List<Object> stuList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.StudentForm");
			int result=teacherService.addStudentByExcel(stuList);
			if(result>0) {
				return "true";
			}else {
				return "false";
			}
	    }

	    /**
	     * 导出到学生Excel
	     * 其他导出注意修改字段 excelHeader 
	     * 其他导出注意修改字段，路径，实体类
	     * @param request
	     * @param response
	     * @param zhuanye_id
	     * @throws IOException
	     */
	    @RequestMapping(value="/erportStudentsExcel")
		public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
			String[] excelHeader = { "学号", "姓名", "性别", "出生年月", "籍贯", "政治面貌","民族", "学院","年级"
					,"专业","专业ID","班级","学生类别","联系方式","辅导员"};  
			HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0);    
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	        params.put("zhuanye_id", zhuanye_id);
	        List<StudentForm> stuList=teacherService.queryStudentList(params);
	        params.clear();
	        for (int i = 0; i < excelHeader.length; i++) {    
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style);  
	            sheet.setColumnWidth(i, 20 * 200);
	        }    
	        for (int i = 0; i < stuList.size(); i++) {    
	            row = sheet.createRow(i + 1);    
	            StudentForm studentForm = stuList.get(i);    
	            row.createCell(0).setCellValue(studentForm.getStudent_number());    
	            row.createCell(1).setCellValue(studentForm.getStudent_name());    
	            row.createCell(2).setCellValue(studentForm.getStudent_sex());    
	            row.createCell(3).setCellValue(studentForm.getStudent_bir());
	            row.createCell(4).setCellValue(studentForm.getStudent_jiguan());
	            row.createCell(5).setCellValue(studentForm.getStudent_mianmao());
	            row.createCell(6).setCellValue(studentForm.getStudent_mingzu());
	            row.createCell(7).setCellValue(studentForm.getStudent_xueyuan());
	            row.createCell(8).setCellValue(studentForm.getStudent_nianji());
	            row.createCell(9).setCellValue(studentForm.getStudent_zhuanye());
	            row.createCell(10).setCellValue(studentForm.getZhuanye_id());
	            row.createCell(11).setCellValue(studentForm.getClass_id());
	            row.createCell(12).setCellValue(studentForm.getStudent_student_type());
	            row.createCell(13).setCellValue(studentForm.getStudent_tel());
	            row.createCell(14).setCellValue(studentForm.getStudent_fudaoyuan());
	        } 
	        String filename = "学生列表.xls";
	        //转码，免得文件名中文乱码
	        filename = URLEncoder.encode(filename,"UTF-8");
	        response.setContentType("application/vnd.ms-excel");    
	        response.setHeader("Content-disposition", "attachment;filename="+filename);    
	        OutputStream ouputStream = response.getOutputStream();    
	        try{
	        	 wb.write(ouputStream);    
	             ouputStream.flush();    
	             ouputStream.close(); 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	        	ouputStream.flush();    
	            ouputStream.close(); 
	        }
		}
	    
	    /**
	     * 下载学生列表模板
	     * @param request
	     * @param response
	     * @throws IOException
	     */
	    @RequestMapping(value="/erportStudentsExcelMuBan")
	    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
	    	String fileName="学生列表模板.xls";
	        String ExcelPath= path + fileName;
	        //获取输入流
	        InputStream bis = new BufferedInputStream(new FileInputStream(new File(ExcelPath)));
	        //假如以中文名下载的话
	        //转码，免得文件名中文乱码
	        fileName = URLEncoder.encode(fileName,"UTF-8");
	        //设置文件下载头
	        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	        response.setContentType("multipart/form-data"); 
	        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
	        int len = 0;
	        while((len = bis.read()) != -1){
	            out.write(len);
	            out.flush();
	        }
	        out.close();
	        bis.close();
	    }
}
