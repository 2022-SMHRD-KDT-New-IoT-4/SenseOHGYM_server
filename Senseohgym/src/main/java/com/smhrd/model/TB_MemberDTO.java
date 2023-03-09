package com.smhrd.model;

public class TB_MemberDTO {
	
	private String mb_name; // 회원이름
	private String mb_card; // 카드번호
	private String mb_birthdate; // 생년월일
	private String mb_gender; // 성별 
	private String gym_name; // 헬스장명
	private char mb_type; //회원유형
	private String mb_joindate; // 가입일자
	
	
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_card() {
		return mb_card;
	}
	public void setMb_card(String mb_card) {
		this.mb_card = mb_card;
	}
	public String getMb_birthdate() {
		return mb_birthdate;
	}
	public void setMb_birthdate(String mb_birthdate) {
		this.mb_birthdate = mb_birthdate;
	}
	public String getMb_gender() {
		return mb_gender;
	}
	public void setMb_gender(String mb_gender) {
		this.mb_gender = mb_gender;
	}
	public String getGym_name() {
		return gym_name;
	}
	public void setGym_name(String gym_name) {
		this.gym_name = gym_name;
	}
	public char getMb_type() {
		return mb_type;
	}
	public void setMb_type(char mb_type) {
		this.mb_type = mb_type;
	}
	public String getMb_joindate() {
		return mb_joindate;
	}
	public void setMb_joindate(String mb_joindate) {
		this.mb_joindate = mb_joindate;
	}

	
	
	
}
