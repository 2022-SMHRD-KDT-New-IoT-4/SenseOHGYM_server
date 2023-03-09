package com.smhrd.model;

public class TB_BoardDTO {
	
	
	private int bo_seq; // 글번호
	private String bo_title; // 글제목
	private String bo_content; // 글내용
	private String bo_file; // 첨부파일
	private int bo_cnt; // 글조회수
	private String bo_dt; // 글작성일자
	private String mb_card; // 카드번호
	
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
	public String getMb_card() {
		return mb_card;
	}
	public void setMb_card(String mb_card) {
		this.mb_card = mb_card;
	}
	
	
	
	
}
