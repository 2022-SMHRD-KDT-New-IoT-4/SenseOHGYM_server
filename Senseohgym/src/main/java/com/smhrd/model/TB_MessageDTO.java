package com.smhrd.model;

public class TB_MessageDTO {
	
	private int msg_seq; // 메시지순번
	private String mb_card; // 카드번호
	private String msg_content; // 메시지내용
	private String msg_date; // 작성일자
	
	public int getMsg_seq() {
		return msg_seq;
	}
	public void setMsg_seq(int msg_seq) {
		this.msg_seq = msg_seq;
	}
	public String getMb_card() {
		return mb_card;
	}
	public void setMb_card(String mb_card) {
		this.mb_card = mb_card;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}
	
	
	

	
}