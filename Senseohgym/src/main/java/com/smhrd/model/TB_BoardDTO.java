package com.smhrd.model;

public class TB_BoardDTO {
	
	private int bo_seq; // 글번호
	private String bo_title;
	private String bo_content;
	private String bo_file;
	private int bo_cnt; // 글조회수
	private String bo_dt; // 글작성일자
	private String mb_id;
	
	public int getBo_seq() {
		return bo_seq;
	}
	public void setBo_seq(int bo_seq) {
		this.bo_seq = bo_seq;
	}
	public String getBo_title() {
		return bo_title;
	}
	public void setBo_title(String bo_title) {
		this.bo_title = bo_title;
	}
	public String getBo_content() {
		return bo_content;
	}
	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}
	public String getBo_file() {
		return bo_file;
	}
	public void setBo_file(String bo_file) {
		this.bo_file = bo_file;
	}
	public int getBo_cnt() {
		return bo_cnt;
	}
	public void setBo_cnt(int bo_cnt) {
		this.bo_cnt = bo_cnt;
	}
	public String getBo_dt() {
		return bo_dt;
	}
	public void setBo_dt(String bo_dt) {
		this.bo_dt = bo_dt;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	
}
