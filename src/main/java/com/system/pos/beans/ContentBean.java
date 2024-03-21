package com.system.pos.beans;

public class ContentBean { //재고물품 정보 객체 - Entity (1:1)
	//필드
	private int content_idx; //인덱스 
	private String content_date; //날짜 
	
	private String content_name; // 상품명
	private String content_code; //상품코드 
	
	private int content_board_idx; //소속 게시판 인덱스 
	private int content_price; //상품 가격 
	private int content_count; //상품 수량 
	
	//get set() 
	public int getContent_idx() {
		return content_idx;
	}
	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}
	public String getContent_date() {
		return content_date;
	}
	public void setContent_date(String content_date) {
		this.content_date = content_date;
	}
	public String getContent_name() {
		return content_name;
	}
	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}
	public String getContent_code() {
		return content_code;
	}
	public void setContent_code(String content_code) {
		this.content_code = content_code;
	}
	public int getContent_board_idx() {
		return content_board_idx;
	}
	public void setContent_board_idx(int content_board_idx) {
		this.content_board_idx = content_board_idx;
	}
	public int getContent_price() {
		return content_price;
	}
	public void setContent_price(int content_price) {
		this.content_price = content_price;
	}
	public int getContent_count() {
		return content_count;
	}
	public void setContent_count(int content_count) {
		this.content_count = content_count;
	}
	
}
