package com.system.pos.beans;

public class SellingBean { //판매 정보 데이터 객체
	//필드
	private int selling_idx;
	private String selling_date;
	
	private String selling_name;
	private int selling_board_idx = 2;
	
	private int selling_price;

	//get set
	public int getSelling_idx() {
		return selling_idx;
	}

	public void setSelling_idx(int selling_idx) {
		this.selling_idx = selling_idx;
	}

	public String getSelling_date() {
		return selling_date;
	}

	public void setSelling_date(String selling_date) {
		this.selling_date = selling_date;
	}

	public String getSelling_name() {
		return selling_name;
	}

	public void setSelling_name(String selling_name) {
		this.selling_name = selling_name;
	}

	public int getSelling_board_idx() {
		return selling_board_idx;
	}

	public void setSelling_board_idx(int selling_board_idx) {
		this.selling_board_idx = selling_board_idx;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}
	
	
	
}
