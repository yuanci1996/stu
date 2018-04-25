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

import com.pojo.ChengJi_jixie;
import com.pojo.PageBean;
import com.service.Teacher_Chengji_jixieService;
import com.utils.ExcelUtil;

@Controller
public class Teacher_Chengji_jixieController {
	
	@Autowired
	@Qualifier(value="teacher_Chengji_jixieService")
	private Teacher_Chengji_jixieService teacher_Chengji_jixieService;
	
	 int zhuanye_id=1;
	HashMap<String, Object> params=new HashMap<String, Object>();
	
	@RequestMapping(value="/addTeacher_Chengji_jixie")
	@ResponseBody
	public String addTeacher_Chengji_jixie(ChengJi_jixie jx) {
		jx.setZhuanye_id(zhuanye_id);
		int result=teacher_Chengji_jixieService.addTeacher_Chengji_jixie(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/modifyTeacher_Chengji_jixie")
	@ResponseBody
	public String modifyTeacher_Chengji_jixie(ChengJi_jixie jx) {
		int result=teacher_Chengji_jixieService.modifyTeacher_Chengji_jixie(jx);
		if(result>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="/deleteTeacher_Chengji_jixie")
	@ResponseBody
	public String deleteTeacher_Chengji_jixie(@RequestParam(value = "ids") String ids) {
		String idStr[] = ids.split(",");
		for (String jixie_id : idStr) {
			teacher_Chengji_jixieService.deleteTeacher_Chengji_jixie(Integer.parseInt(jixie_id));
		}
		return "ture";
	}
	
	@RequestMapping(value="/queryTeacher_Chengji_jixieList")
	@ResponseBody
	public ModelMap queryTeacher_Chengji_jixieList(@RequestParam("page") Integer page,HttpServletRequest req,
			@RequestParam("rows") Integer rows,@RequestParam(value = "student_number", required = false) String student_number) {
		ModelMap model=new ModelMap();
		PageBean pageBean = new PageBean(page, rows);
		params.put("pageStart", pageBean.getPageStart());
		params.put("rows", pageBean.getRows());
		params.put("student_number", student_number);
		params.put("zhuanye_id", zhuanye_id);
		model.put("total", teacher_Chengji_jixieService.queryTeacher_Chengji_jixieListTotal(params));
		model.put("rows", teacher_Chengji_jixieService.queryTeacher_Chengji_jixieList(params));
		params.clear();
		return model;
	}
	
	/**
     * ����ѧ����е�ɼ�Excel
     * ��������ע���޸��ֶΣ�·����ʵ����
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addTeacher_Chengji_jixieByExcel")
    @ResponseBody
    public String addTeacher_Chengji_jixieByExcel(MultipartFile file,HttpServletRequest request) throws Exception{
        String path = request.getSession().getServletContext().getRealPath("upload/importExcel")+"/";
        String fileName = file.getOriginalFilename();
        String ExcelPath= path + fileName;
        File dir = new File(path,fileName);        
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile�Դ��Ľ�������
        file.transferTo(dir);
        String keyValue ="ѧ��:student_number,����:student_name,����һ�廯����������Ƽ���:kc_jdyth,���ؼ�����Ӧ��:kc_skjs,��еCAD/CAEӦ�ü�������:kc_jxcad,"
        		+ "���紫����PLC:kc_jdcd,LabVIEW����������Ӧ��:kc_labview,����ʵϰ:kc_scsx,Ƕ��ʽϵͳ���:kc_qrsxt,�������ʵѵ(B):kc_rjsx,"
        		+ "רҵID:zhuanye_id,רҵ:zhuanye_name,ѧ��:cj_xueqi";
        List<Object> jixieList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.ChengJi_jixie");
		int result=teacher_Chengji_jixieService.addTeacher_Chengji_jixieByExcel(jixieList);
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
    @RequestMapping(value="/erportTeacher_Chengji_jixieByExcel")
	public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
    	String[] excelHeader = {"ѧ��","����","����һ�廯����������Ƽ���","���ؼ�����Ӧ��","��еCAD/CAEӦ�ü�������",
        		"���紫����PLC","LabVIEW����������Ӧ��","����ʵϰ","Ƕ��ʽϵͳ���","�������ʵѵ(B)",
        		"רҵID","רҵ","ѧ��"}; 
		HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("sheet1"); 
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        params.put("zhuanye_id", zhuanye_id);
        List<ChengJi_jixie> jixieList=teacher_Chengji_jixieService.queryTeacher_Chengji_jixieList(params);
        params.clear();
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);  
            sheet.setColumnWidth(i, 20 * 200);
        }    
        for (int i = 0; i < jixieList.size(); i++) {    
            row = sheet.createRow(i + 1);    
            ChengJi_jixie chengJi_jixie = jixieList.get(i);    
            row.createCell(0).setCellValue(chengJi_jixie.getStudent_number());    
            row.createCell(1).setCellValue(chengJi_jixie.getStudent_name());    
            row.createCell(2).setCellValue(chengJi_jixie.getKc_jdyth());    
            row.createCell(3).setCellValue(chengJi_jixie.getKc_skjs());
            row.createCell(4).setCellValue(chengJi_jixie.getKc_jxcad());
            row.createCell(5).setCellValue(chengJi_jixie.getKc_jdcd());
            row.createCell(6).setCellValue(chengJi_jixie.getKc_labview());
            row.createCell(7).setCellValue(chengJi_jixie.getKc_scsx());
            row.createCell(8).setCellValue(chengJi_jixie.getKc_qrsxt());
            row.createCell(9).setCellValue(chengJi_jixie.getKc_rjsx());
            row.createCell(10).setCellValue(chengJi_jixie.getZhuanye_id());
            row.createCell(11).setCellValue(chengJi_jixie.getZhuanye_name());
            row.createCell(12).setCellValue(chengJi_jixie.getCj_xueqi());
        } 
        String filename = "�������ɼ��б�.xls";
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
    @RequestMapping(value="/erportTeacher_Chengji_jixieExcelMuBan")
    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
    	String fileName="�������ɼ�ģ��.xls";
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
