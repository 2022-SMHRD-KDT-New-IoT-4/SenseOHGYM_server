package com.smhrd.model;

public class TB_ReservationDTO {

	private int rev_seq; // 예약순번
	private String mb_card; // 카드번호
	private String rs_machine; // 사용기구명
	private int use_time; // 사용시간
	private String reg_time; // 접수시간

	public TB_ReservationDTO(String mb_card, String rs_machine, int use_time) {
		super();
		this.mb_card = mb_card;
		this.rs_machine = rs_machine;
		this.use_time = use_time;
	}

	public int getRev_seq() {
		return rev_seq;
	}

	public void setRev_seq(int rev_seq) {
		this.rev_seq = rev_seq;
	}

	public String getMb_card() {
		return mb_card;
	}

	public void setMb_card(String mb_card) {
		this.mb_card = mb_card;
	}

	public String getRs_machine() {
		return rs_machine;
	}

	public void setRs_machine(String rs_machine) {
		this.rs_machine = rs_machine;
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
