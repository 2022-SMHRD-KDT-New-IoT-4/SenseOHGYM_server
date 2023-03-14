package com.smhrd.model;

public class TB_UserExerciseDTO {
	

	private int exr_seq; // 예약순번
	private String mb_card; //카드번호
	private int rs_usetime; // 사용시간
	private String rev_machine; // 사용기구명 
	private int reg_date; // 접수시간
	

	

	public TB_UserExerciseDTO(String mb_card) {
		super();
		this.mb_card = mb_card;
	}





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





	public String getRev_machine() {
		return rev_machine;
	}





	public void setRev_machine(String rev_machine) {
		this.rev_machine = rev_machine;
	}





	public int getReg_date() {
		return reg_date;
	}





	public void setReg_date(int reg_date) {
		this.reg_date = reg_date;
	}
	

	
	
	
}
