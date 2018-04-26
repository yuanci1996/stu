package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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

import com.pojo.CchengjiForm;
import com.pojo.PageBean;
import com.pojo.TeacherForm;
import com.service.TeacherCchengjiService;
import com.utils.ExcelUtil;


@Controller
public class TeacherCchengjiController {

	@Autowired
	@Qualifier(value="teacherCchengjiService")
	private TeacherCchengjiService teacherCchengjiService;
	
	int zhuanye_id=0;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacherCchengji")
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
	
	@RequestMapping(value="/modifyTeacherCchengji")
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
	
	@RequestMapping(value="/deleteTeacherCchengji")
	@ResponseBody
	public String deleteTeacher_Cchengji(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jixie_id : idStr) {
			teacherCchengjiService.deleteCchengji(jixie_id);
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacherCchengjiList")
	@ResponseBody
	public ModelMap queryTeacher_CchengjiList(@RequestParam("page") Integer page,HttpServletRequest req,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		HttpSession session=req.getSession();
		TeacherForm user=(TeacherForm) session.getAttribute("user");
		zhuanye_id=user.getZhuanye_id();
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		if(zhuanye_id!=0) {
		params.put("zhuanye_id", zhuanye_id);
		}
		model.put("total", teacherCchengjiService.queryCchengjiListJcTotal(params));
		model.put("rows", teacherCchengjiService.queryCchengjiList(params));
		return model;
	}
	  /**
     * ����ѧ��Excel
     * ��������ע���޸��ֶΣ�·����ʵ����
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/importCcjListByExcel")
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
        String keyValue ="ѧ��:student_number,����:name,����Ա����:Fosc,���õ÷�:Ftosc,�ͷ��۷�:Ftwsc,"
        		+ "�����ɼ�:Frsc,�����ɼ�:Ffsc,�۲�ɼ�:Fsc,רҵID:zhuanye_id";
        List<Object> ccjList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.CchengjiForm");
		int result=teacherCchengjiService.addStudentCcjByExcel(ccjList);
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
    @RequestMapping(value="/erportCcjExcel")
	public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
		String[] excelHeader = { "ѧ��", "����", "����Ա����", "���õ÷�", "�ͷ��۷�", "�����ɼ�","�����ɼ�", "�۲�ɼ�","רҵID"};  
		HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("sheet1"); 
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        params.put("zhuanye_id", zhuanye_id);
        List<CchengjiForm> ccjList=teacherCchengjiService.queryCchengjiList(params);
        params.clear();
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);  
            sheet.setColumnWidth(i, 20 * 200);
        }    
        for (int i = 0; i < ccjList.size(); i++) {    
            row = sheet.createRow(i + 1);    
            CchengjiForm cchengjiForm = ccjList.get(i);   
            row.createCell(0).setCellValue(cchengjiForm.getStudent_number());    
            row.createCell(1).setCellValue(cchengjiForm.getName());    
            row.createCell(2).setCellValue(cchengjiForm.getFosc());    
            row.createCell(3).setCellValue(cchengjiForm.getFtosc());
            row.createCell(4).setCellValue(cchengjiForm.getFtwsc());
            row.createCell(5).setCellValue(cchengjiForm.getFrsc());
            row.createCell(6).setCellValue(cchengjiForm.getFfsc());
            row.createCell(7).setCellValue(cchengjiForm.getFsc());
            row.createCell(8).setCellValue(cchengjiForm.getZhuanye_id());
        } 
        String filename = "�۲�.xls";
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
    @RequestMapping(value="/erportCcjExcelMuBan")
    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
    	String fileName="�۲�ģ��.xls";
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
