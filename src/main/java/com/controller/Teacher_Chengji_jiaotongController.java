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
     * ����ѧ����е�ɼ�Excel
     * ��������ע���޸��ֶΣ�·����ʵ����
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
        //MultipartFile�Դ��Ľ�������
        file.transferTo(dir);
        String keyValue ="ѧ��:student_number,����:student_name,��·����רҵӢ��:kc_tlys,��·������֯:kc_tlhy,��ͨ�滮�����뷽��:kc_jtgh,"
        		+ "��·�г�����:kc_tlxc,��·�������:kc_tllc,����·������֯���γ����:kc_tlhy_kcsj,��ͨ�����ۺ�ʵѵ:kc_jtys_sx,�������ʵѵ(B):kc_rjgc_sx,"
        		+ "רҵID:zhuanye_id,רҵ:zhuanye_name,ѧ��:cj_xueqi";
        List<Object> jiaotongList =  ExcelUtil.readXls(ExcelPath,ExcelUtil.getMap(keyValue),"com.pojo.ChengJi_jiaotong");
		int result=teacher_Chengji_jiaotongService.addTeacher_Chengji_jiaotongByExcel(jiaotongList);
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
    @RequestMapping(value="/erportTeacher_Chengji_jiaotongByExcel")
	public void erportStudentsExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "zhuanye_id", required = false) int zhuanye_id) throws IOException {
    	String[] excelHeader = {"ѧ��","����","��·����רҵӢ��","��·������֯","��ͨ�滮�����뷽��",
        		"��·�г�����","��·�������","����·������֯���γ����","��ͨ�����ۺ�ʵѵ","�������ʵѵ(B)",
        		"רҵID","רҵ","ѧ��"}; 
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
        String filename = "�����ͨ����ɼ��б�.xls";
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
    @RequestMapping(value="/erportTeacher_Chengji_jiaotongExcelMuBan")
    public void exportStuExcelMuban(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String path = request.getSession().getServletContext().getRealPath("excel")+"/";
    	String fileName="�����ͨ����ɼ�ģ��.xls";
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
