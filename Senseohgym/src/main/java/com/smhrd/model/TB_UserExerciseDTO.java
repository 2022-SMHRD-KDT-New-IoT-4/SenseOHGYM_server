package com.smhrd.model;

public class TB_UserExerciseDTO {
	

	private int exr_seq; // 운동순번
	private String mb_card; //카드번호
	private int rs_usetime; // 사용시간
	private String rs_machine; // 사용기구명 
	private int reg_date; // 등록시간
	
	
	public int getExr_seq() {
		return exr_seq;
	}
	public void setExr_seq(int exr_seq) {
		this.exr_seq = exr_seq;
	}
	public String getMb_card() {
		return mb_card;
	}
	public void setMb_card(String mb_card) {
		this.mb_card = mb_card;
	}
	public int getRs_usetime() {
		return rs_usetime;
	}
	public void setRs_usetime(int rs_usetime) {
		this.rs_usetime = rs_usetime;
	}
	public String getRs_machine() {
		return rs_machine;
	}
	public void setRs_machine(String rs_machine) {
		this.rs_machine = rs_machine;
	}
	public int getReg_date() {
		return reg_date;
	}
	public void setReg_date(int reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
