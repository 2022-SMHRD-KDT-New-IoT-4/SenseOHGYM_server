package com.smhrd.model;

public class TB_ReservationDTO {
	
	private int rev_seq; // 예약순번
	private String mb_id;
	private String rs_machine; // 사용기구명
	private String rs_time; // 예약시간
	private int use_time; // 사용시간
	private String reg_time; // 접수시간
	
	public int getRev_seq() {
		return rev_seq;
	}
	public void setRev_seq(int rev_seq) {
		this.rev_seq = rev_seq;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getRs_machine() {
		return rs_machine;
	}
	public void setRs_machine(String rs_machine) {
		this.rs_machine = rs_machine;
	}
	public String getRs_time() {
		return rs_time;
	}
	public void setRs_time(String rs_time) {
		this.rs_time = rs_time;
	}
	public int getUse_time() {
		return use_time;
	}
	public void setUse_time(int use_time) {
		this.use_time = use_time;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	
}
