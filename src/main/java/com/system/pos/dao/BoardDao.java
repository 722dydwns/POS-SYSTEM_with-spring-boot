package com.system.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.system.pos.beans.ContentBean;
import com.system.pos.beans.SellingBean;

public class BoardDao { 
	
	// JDBC 관리 객체를 주입 받는다. -> Spring JDBC 활용한 쿼리 작성 
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Mapper를 주입받는다. -> mapRow() 메소드 재정의함 - 여러 행 Select 시 매핑 
	private BoardMapper boardMapper;
	
	private SellingInfoMapper sellingMapper;

	//생성자 - 의존 주입받는다.
	public BoardDao(BoardMapper boardMapper, SellingInfoMapper sellingMapper) {
		this.boardMapper = boardMapper;
		this.sellingMapper = sellingMapper;
	}
	
	// 메뉴 이름 가져오기
	public String getBoardInfoName(int board_info_idx) {
		String sql = "SELECT board_info_name FROM board_info_table WHERE board_info_idx= ?";
		return jdbcTemplate.queryForObject(sql, String.class, board_info_idx);
	}

	// 사용자가 쓴 입고 물품 쓰기
	public void addContentInfo(ContentBean writeContentBean) {
		String sql = "INSERT INTO content_table(content_name,content_price, content_count, content_board_idx) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, writeContentBean.getContent_name(), writeContentBean.getContent_price(),
				writeContentBean.getContent_count(), writeContentBean.getContent_board_idx());
	}

	// 상품 재고 목록 가져오기
	public List<ContentBean> getContentList(int board_info_idx) {
		String sql = "SELECT content_idx, content_date as content_date, content_name, content_price, content_count FROM content_table WHERE content_board_idx = ? ORDER BY content_idx DESC ";
		List<ContentBean> contentBean = jdbcTemplate.query(sql, boardMapper, board_info_idx);

		return contentBean;
	}

	// -> 판매 위함 '상품명 정보 검색 기능' 상품명 입력받으면 가격, 수량 가져오기
	public List<ContentBean> getSearchBean(String content_name) {
		String sql = "SELECT content_idx, content_name, content_price, content_count, content_date FROM content_table WHERE content_name = ?";
		List<ContentBean> list = jdbcTemplate.query(sql, boardMapper, content_name);
		return list;
	}

	// '상품명 판매 계산 기능' 사용자가 판매 상품명 입력 -> 가격 가져오기
	public int getSellPrice(String content_name) {
		String sql = "SELECT content_price FROM content_table WHERE content_name = ?";
		int price = jdbcTemplate.queryForObject(sql, Integer.class, content_name);
		return price;
	}

	// 판매 정보 저장하기
	public void addSellingInfo(SellingBean sellingInfo) {
		String sql = "INSERT INTO selling_table(selling_name, selling_price) VALUES (?, ?)";

		jdbcTemplate.update(sql, sellingInfo.getSelling_name(), sellingInfo.getSelling_price());
	}

	//판매 정보 목록 모두 가져오기
	public List<SellingBean> getSellingInfo() {
		String sql = "SELECT * FROM selling_table ORDER BY selling_idx DESC";

		List<SellingBean> list = jdbcTemplate.query(sql, sellingMapper);
		return list;
	}

	// '최근 일주일' 통계 정보 가져오기
	public List<SellingBean> getOneWeekSellingInfo() {
		String sql = "SELECT * FROM selling_table WHERE selling_date BETWEEN DATE_ADD(NOW(), INTERVAL -1 WEEK ) AND NOW() ORDER BY selling_date ASC";

		List<SellingBean> list = jdbcTemplate.query(sql, sellingMapper);

		return list;
	}

	// '최근 한달' 통계 정보 가져오기
	public List<SellingBean> getOneMonthSellingInfo() {
		String sql = "SELECT * FROM selling_table WHERE selling_date BETWEEN DATE_ADD(NOW(), INTERVAL -1 MONTH ) AND NOW() ORDER BY selling_date ASC";
		List<SellingBean> list = jdbcTemplate.query(sql, sellingMapper);
		return list;
	}

	// 지정날짜 판매 정보 가져오기
	public List<SellingBean> getTotalInfo(String start, String end) {
		String sql = "select * from selling_table where selling_date between date(?) and date(?) +1  order by selling_date ASC";
		List<SellingBean> list = jdbcTemplate.query(sql, sellingMapper, start, end);
		return list;
	}

}
