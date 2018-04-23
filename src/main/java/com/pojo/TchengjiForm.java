package com.pojo;

public class TchengjiForm {
	private String student_number;
	private String Tchengji_name;
	private String Tchengji_xueqi;
	private String Tchengji_sco;
	public TchengjiForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStudent_number() {
		return student_number;
	}
	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}
	public String getTchengji_name() {
		return Tchengji_name;
	}
	public void setTchengji_name(String tchengji_name) {
		Tchengji_name = tchengji_name;
	}
	public String getTchengji_xueqi() {
		return Tchengji_xueqi;
	}
	public void setTchengji_xueqi(String tchengji_xueqi) {
		Tchengji_xueqi = tchengji_xueqi;
	}
	public String getTchengji_sco() {
		return Tchengji_sco;
	}
	public void setTchengji_sco(String tchengji_sco) {
		Tchengji_sco = tchengji_sco;
	}
	public TchengjiForm(String student_number, String tchengji_name,
			String tchengji_xueqi, String tchengji_sco) {
		super();
		this.student_number = student_number;
		Tchengji_name = tchengji_name;
		Tchengji_xueqi = tchengji_xueqi;
		Tchengji_sco = tchengji_sco;
	}
	
}
