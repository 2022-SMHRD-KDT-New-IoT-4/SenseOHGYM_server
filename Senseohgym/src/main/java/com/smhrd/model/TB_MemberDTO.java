package com.smhrd.model;

public class TB_MemberDTO {
	
	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private int mb_birthdate;
	private String mb_gender;
	private int mb_height;
	private int mb_weight;
	private String mb_type; // 회원유형
	private String mb_card; 
	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public int getMb_birthdate() {
		return mb_birthdate;
	}
	public void setMb_birthdate(int mb_birthdate) {
		this.mb_birthdate = mb_birthdate;
	}
	public String getMb_gender() {
		return mb_gender;
	}
	public void setMb_gender(String mb_gender) {
		this.mb_gender = mb_gender;
	}
	public int getMb_height() {
		return mb_height;
	}
	public void setMb_height(int mb_height) {
		this.mb_height = mb_height;
	}
	public int getMb_weight() {
		return mb_weight;
	}
	public void setMb_weight(int mb_weight) {
		this.mb_weight = mb_weight;
	}
	public String getMb_type() {
		return mb_type;
	}
	public void setMb_type(String mb_type) {
		this.mb_type = mb_type;
	}
	public String getMb_card() {
		return mb_card;
	}
	public void setMb_card(String mb_card) {
		this.mb_card = mb_card;
	}
	
}
