package com.pojo;

public class CchengjiForm {
	private int cc_id;
	private int zhuanye_id;
	private String student_number;
	private String name;
	private String Fosc;
	private String Ftosc;
	private String Ftwsc;
	private String Frsc;
	private String Ffsc;
	private String Fsc;
	private String sco;
	public CchengjiForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CchengjiForm(String student_number, String name, String fosc, String ftosc,
			String ftwsc, String frsc, String ffsc, String fsc, String sco) {
		super();
		this.student_number = student_number;
		this.name = name;
		Fosc = fosc;
		Ftosc = ftosc;
		Ftwsc = ftwsc;
		Frsc = frsc;
		Ffsc = ffsc;
		Fsc = fsc;
		this.sco = sco;
	}
	
	public int getZhuanye_id() {
		return zhuanye_id;
	}
	public void setZhuanye_id(int zhuanye_id) {
		this.zhuanye_id = zhuanye_id;
	}
	public int getCc_id() {
		return cc_id;
	}
	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
	}
	public String getStudent_number() {
		return student_number;
	}
	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFosc() {
		return Fosc;
	}
	public void setFosc(String fosc) {
		Fosc = fosc;
	}
	public String getFtosc() {
		return Ftosc;
	}
	public void setFtosc(String ftosc) {
		Ftosc = ftosc;
	}
	public String getFtwsc() {
		return Ftwsc;
	}
	public void setFtwsc(String ftwsc) {
		Ftwsc = ftwsc;
	}
	public String getFrsc() {
		return Frsc;
	}
	public void setFrsc(String frsc) {
		Frsc = frsc;
	}
	public String getFfsc() {
		return Ffsc;
	}
	public void setFfsc(String ffsc) {
		Ffsc = ffsc;
	}
	public String getFsc() {
		return Fsc;
	}
	public void setFsc(String fsc) {
		Fsc = fsc;
	}
	public String getSco() {
		return sco;
	}
	public void setSco(String sco) {
		this.sco = sco;
	}
	
}
