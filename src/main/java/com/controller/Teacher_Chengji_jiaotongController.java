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

import com.pojo.ChengJi_jiaotong;
import com.pojo.PageBean;
import com.service.Teacher_Chengji_jiaotongService;
import com.utils.ExcelUtil;

@Controller
public class Teacher_Chengji_jiaotongController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_jiaotongService")
	private Teacher_Chengji_jiaotongService teacher_Chengji_jiaotongService;
	
	 int zhuanye_id=2;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_jiaotong")
	@ResponseBody
	public String addTeacher_Chengji_jiaotong(ChengJi_jiaotong jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_jiaotongService.addTeacher_Chengji_jiaotong(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_jiaotong")
	@ResponseBody
	public String modifyTeacher_Chengji_jiaotong(ChengJi_jiaotong jx) {
		int result=teacher_Chengji_jiaotongService.modifyTeacher_Chengji_jiaotong(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_jiaotong")
	@ResponseBody
	public String deleteTeacher_Chengji_jiaotong(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jiaotong_id : idStr) {
			teacher_Chengji_jiaotongService.deleteTeacher_Chengji_jiaotong(Integer.parseInt(jiaotong_id));
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_jiaotongList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_jiaotongList(@RequestParam("page") Integer page,HttpServletRequest req,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		params.put("zhuanye_id", zhuanye_id);
		model.put("total", teacher_Chengji_jiaotongService.queryTeacher_Chengji_jiaotongListTotal(params));
		model.put("rows", teacher_Chengji_jiaotongService.queryTeacher_Chengji_jiaotongList(params));
		return model;
	}
	
	/**
     * 导入学生机械成绩Excel
     * 其他导入注意修改字段，路径，实体类
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addTeacher_Chengji_jiaotongByExcel")
    @ResponseBody
    public String addTeacher_Chengji_jiaotongByExcel(MultipartFile file,HttpServletRequest request) throws Exception{
        String path = request.getSession().getServletContext().getRealPath("upload/importExcel")+"/";
        String fileName = file.getOriginalFilename();
        String ExcelPath= path + fileName;
        File dir = new File(path,fileName);        
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(dir);
        String keyValue ="学号:student_number,姓名:student_name,铁路运输专业英语:kc_tlys,铁路货运组织:kc_tlhy,交通规划理论与方法:kc_jtgh,"
        		+ "铁路行车规章:kc_tlxc,铁路冷藏运输:kc_tllc,《铁路货运组织》课程设计:kc_tlhy_kcsj,交通运输综合实训:kc_jtys_sx,软件工程实训(B):kc_rjgc_sx,"
        		+ "专业ID:zhuanye_id,专业:zhuanye_name,学期:cj_xueqi";
        List<Object> jiaotongList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.ChengJi_jiaotong");
		int result=teacher_Chengji_jiaotongService.addTeacher_Chengji_jiaotongByExcel(jiaotongList);
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
    @RequestMapping(value="/erportTeacher_Chengji_jiaotongByExcel")
	public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
    	String[] excelHeader = {"学号","姓名","铁路运输专业英语","铁路货运组织","交通规划理论与方法",
        		"铁路行车规章","铁路冷藏运输","《铁路货运组织》课程设计","交通运输综合实训","软件工程实训(B)",
        		"专业ID","专业","学期"}; 
		HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("sheet1"); 
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        params.put("zhuanye_id", zhuanye_id);
        List<ChengJi_jiaotong> jiaotongList=teacher_Chengji_jiaotongService.queryTeacher_Chengji_jiaotongList(params);
        params.clear();
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);  
            sheet.setColumnWidth(i, 20 * 200);
        }    
        for (int i = 0; i < jiaotongList.size(); i++) {    
            row = sheet.createRow(i + 1);    
            ChengJi_jiaotong chengJi_jiaotong = jiaotongList.get(i);    
            row.createCell(0).setCellValue(chengJi_jiaotong.getStudent_number());    
            row.createCell(1).setCellValue(chengJi_jiaotong.getStudent_name());    
            row.createCell(2).setCellValue(chengJi_jiaotong.getKc_tlys());    
            row.createCell(3).setCellValue(chengJi_jiaotong.getKc_tlhy());
            row.createCell(4).setCellValue(chengJi_jiaotong.getKc_jtgh());
            row.createCell(5).setCellValue(chengJi_jiaotong.getKc_tlxc());
            row.createCell(6).setCellValue(chengJi_jiaotong.getKc_tllc());
            row.createCell(7).setCellValue(chengJi_jiaotong.getKc_tlhy_kcsj());
            row.createCell(8).setCellValue(chengJi_jiaotong.getKc_jtys_sx());
            row.createCell(9).setCellValue(chengJi_jiaotong.getKc_rjgc_sx());
            row.createCell(10).setCellValue(chengJi_jiaotong.getZhuanye_id());
            row.createCell(11).setCellValue(chengJi_jiaotong.getZhuanye_name());
            row.createCell(12).setCellValue(chengJi_jiaotong.getCj_xueqi());
        } 
        String filename = "软件交通运输成绩列表.xls";
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
    @RequestMapping(value="/erportTeacher_Chengji_jiaotongExcelMuBan")
    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
    	String fileName="软件交通运输成绩模板.xls";
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
