package com.system.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.pos.beans.ContentBean;
import com.system.pos.beans.SellingBean;
import com.system.pos.dao.BoardDao;

@Service
public class BoardService { 
	@Autowired
	private BoardDao boardDao;
	
	//사용자가 쓴 write 내용 객체를 Db에 저장하는 코드 
	public void addContentInfo(ContentBean writeContentBean) {
		boardDao.addContentInfo(writeContentBean); //매퍼에 추가 내용물 담기 
	}
	
	//전체 상품 목록가져오기 
	public List<ContentBean> getContentList(int board_info_idx) {
		return boardDao.getContentList(board_info_idx);
	}
	
	//'인덱스' 받으면 이름 출력하는 코드 
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}

	//상품명 입력받으면 -> 해당 상품의 상품 정보 목록 가져오는 코드 
	public List<ContentBean> getSearchBean(String content_name) {
		return boardDao.getSearchBean(content_name);
	}
	//판매 시 상품명 -> 가격 가져오는 코드 
	public int getSellPrice(String content_name) {
		return boardDao.getSellPrice(content_name);
	}
	
	//판매 정보 업로드
	public void addSellingInfo(SellingBean sellingInfo) {
		boardDao.addSellingInfo(sellingInfo);
	}
	
	//모든 판매 목록 가져오기 
	public List<SellingBean> getSellingInfo() {
		return boardDao.getSellingInfo();
	}
	
	//최근 일주일 판매 정보 가져오기 
	public List<SellingBean> getOneWeekSellingInfo() {
		return boardDao.getOneWeekSellingInfo();
	}
	//최근 한달 판매 정보 가져오기 
	public List<SellingBean> getOneMonthSellingInfo(){
		return boardDao.getOneMonthSellingInfo();
	}
	
	//날짜 조회해서 가져오기
	public List<SellingBean> getTotalInfo(String start, String end){
		return boardDao.getTotalInfo(start, end);
	}
	
}
