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
     * 导入学生Excel
     * 其他导入注意修改字段，路径，实体类
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
        //MultipartFile自带的解析方法
        file.transferTo(dir);
        String keyValue ="学号:student_number,姓名:name,辅导员评分:Fosc,表彰得分:Ftosc,惩罚扣分:Ftwsc,"
        		+ "体育成绩:Frsc,智育成绩:Ffsc,综测成绩:Fsc,专业ID:zhuanye_id";
        List<Object> ccjList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.CchengjiForm");
		int result=teacherCchengjiService.addStudentCcjByExcel(ccjList);
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
    @RequestMapping(value="/erportCcjExcel")
	public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
		String[] excelHeader = { "学号", "姓名", "辅导员评分", "表彰得分", "惩罚扣分", "体育成绩","智育成绩", "综测成绩","专业ID"};  
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
        String filename = "综测.xls";
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
    @RequestMapping(value="/erportCcjExcelMuBan")
    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
    	String fileName="综测模板.xls";
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
