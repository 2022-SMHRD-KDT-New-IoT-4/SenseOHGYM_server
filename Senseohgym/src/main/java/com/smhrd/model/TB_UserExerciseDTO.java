package com.smhrd.model;

public class TB_UserExerciseDTO {
	
	private int exr_seq; // 운동순번
	private String mb_id;
	private int rs_usetime; // 사용시간
	private int rev_seq; // 예약순번
	private String reg_date; // 등록시간
	
	public int getExr_seq() {
		return exr_seq;
	}
	public void setExr_seq(int exr_seq) {
		this.exr_seq = exr_seq;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getRs_usetime() {
		return rs_usetime;
	}
	public void setRs_usetime(int rs_usetime) {
		this.rs_usetime = rs_usetime;
	}
	public int getRev_seq() {
		return rev_seq;
	}
	public void setRev_seq(int rev_seq) {
		this.rev_seq = rev_seq;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

}
