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
	 * ��ʦ��¼
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
		attr.addFlashAttribute("Msg", "�˺Ż�������󣡣�");
		return "all_login";
		}
	}
	
	/**
	 * ͨ�������ҳ�����Լ�ȡ����ʦ�İ༶id��ȡ������ͬ�༶id��ѧ���б�
	 * ͨ��ѧ�Ų�ѯʱ����ѧ�Ų���
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
	 * ͨ�������ѧ����ɾ��ѧ����Ϣ
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
	 * ͨ�������ѧ��ʵ�������޸�ѧ������
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
	 * ͨ�������ѧ�������Լ���ȡ����¼��ʦ�İ༶id������ѧ��
	 * ͬʱ����ѧ���ĳ�ʼ����
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/te_adn_addStudent")
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

	    /**
	     * ����ѧ��Excel
	     * ��������ע���޸��ֶΣ�·����ʵ����
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
	        //MultipartFile�Դ��Ľ�������
	        file.transferTo(dir);
	        String keyValue ="ѧ��:student_number,����:student_name,�Ա�:student_sex,��������:student_bir,����:student_jiguan,"
	        		+ "������ò:student_mianmao,����:student_mingzu,ѧԺ:student_xueyuan,�꼶:student_nianji,רҵ:student_zhuanye,�༶:class_id,ѧ�����:student_student_type,��ϵ��ʽ:student_tel,"
	        		+ "רҵID:zhuanye_id,����Ա:student_fudaoyuan";
	        List<Object> stuList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.StudentForm");
			int result=teacherService.addStudentByExcel(stuList);
			if(result>0) {
				return "true";
			}else {
				return "false";
			}
	    }

	    /**
	     * ������ѧ��Excel
	     * ��������ע���޸��ֶ� excelHeader 
	     * ��������ע���޸��ֶΣ�·����ʵ����
	     * @param request
	     * @param response
	     * @param zhuanye_id
	     * @throws IOException
	     */
	    @RequestMapping(value="/erportStudentsExcel")
		public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
			String[] excelHeader = { "ѧ��", "����", "�Ա�", "��������", "����", "������ò","����", "ѧԺ","�꼶"
					,"רҵ","רҵID","�༶","ѧ�����","��ϵ��ʽ","����Ա"};  
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
	        String filename = "ѧ���б�.xls";
	        //ת�룬����ļ�����������
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
	     * ����ѧ���б�ģ��
	     * @param request
	     * @param response
	     * @throws IOException
	     */
	    @RequestMapping(value="/erportStudentsExcelMuBan")
	    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
	    	String fileName="ѧ���б�ģ��.xls";
	        String ExcelPath= path + fileName;
	        //��ȡ������
	        InputStream bis = new BufferedInputStream(new FileInputStream(new File(ExcelPath)));
	        //���������������صĻ�
	        //ת�룬����ļ�����������
	        fileName = URLEncoder.encode(fileName,"UTF-8");
	        //�����ļ�����ͷ
	        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
	        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����  
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
