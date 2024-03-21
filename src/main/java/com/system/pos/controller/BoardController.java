package com.system.pos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.pos.beans.ContentBean;
import com.system.pos.beans.DayCommand;
import com.system.pos.beans.SellingBean;
import com.system.pos.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	//Service
	@Autowired
	private BoardService boardService;

	@GetMapping("/main") //메뉴 게시판 인덱스 번호 받기 타입 불일치 에러뜸
	public String main(@RequestParam("board_info_idx") int board_info_idx,
						Model model) {
		model.addAttribute("board_info_idx", board_info_idx);
		
		model.addAttribute("A", board_info_idx); //if jsp 파일 분기문 쓸 용도 
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName); //뷰에 전달할 변수 세팅
		
		//상품목록 가져오기 - 메뉴1
		List<ContentBean> contentList = boardService.getContentList(board_info_idx);
		model.addAttribute("contentList", contentList);
		//판매목록 띄우기 
		if(board_info_idx == 2) {
			List<SellingBean> sellingList = boardService.getSellingInfo();//전체 다 가져옴 
			model.addAttribute("sellingList", sellingList);
		}
		return "board/main";
	}
	
	//추가 상품목록 쓰기 
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
			@RequestParam("board_info_idx") int board_info_idx) {
		
		writeContentBean.setContent_board_idx(board_info_idx); //파라미터에 들어온 보드 게시판 인덱스 번호를 얘한테도 세팅해줌 
		
		return "board/write";
	}

	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";
		}
		boardService.addContentInfo(writeContentBean);
		
		return "board/write_success";
	}
	
	//상품검색 - 이름 입력 시 -> 가격, 수량 정보 가져오기 
	@RequestMapping("/search")
	public String search(@ModelAttribute("SearchBean") ContentBean SearchBean, Model model) {
		
		if(SearchBean.getContent_name() != null ) {
			List<ContentBean> researchBean = boardService.getSearchBean(SearchBean.getContent_name());
			model.addAttribute("researchBean", researchBean);
		}
		return "board/search";
	}

	//판매처리 (계산 + 판매정보 저장)
	@RequestMapping("/selling")
	public String selling(@ModelAttribute("sellingBean") ContentBean sellingBean, Model model) {
		//상품계산하기 
		if(sellingBean.getContent_name() != null ) {			
			//총가격 계산
			int price = boardService.getSellPrice(sellingBean.getContent_name()); //상품명에 대한 가격 받아서 
			price = price * sellingBean.getContent_count();
			
			sellingBean.setContent_price(price); //총가격 세팅			
			sellingBean.setContent_board_idx(2);
			
			//계산과 동시에 -> 판매정보 DB에 저장
			SellingBean InfoSell = new SellingBean();

			InfoSell.setSelling_name(sellingBean.getContent_name());//이름 저장
			InfoSell.setSelling_price(sellingBean.getContent_price()); //가격저장
			
			boardService.addSellingInfo(InfoSell);			
			
			model.addAttribute("price", price); //판매 가격만 뷰에 바로 보내기
			
		} 
		return "board/selling";
	}
	
	
	//최근 일주일 판매 정보 가져와서 총액 출력
	@RequestMapping("/oneWeekTotal")
	public String oneWeek(Model model) {
		List<SellingBean> oneWeekList = boardService.getOneWeekSellingInfo();
		
		int price = 0;
		for(SellingBean A : oneWeekList) {
			price += A.getSelling_price();
		}
		model.addAttribute("price", price);
		
		model.addAttribute("oneWeekList",oneWeekList); //최근 목록도 넣기 
		
		return "board/oneWeekTotal";
	
	}
	
	//최근 한달 판매 정보 목록 
	@RequestMapping("/oneMonthTotal")
	public String oneModnthTotal(Model model) {
		List<SellingBean> oneMonthList = boardService.getOneMonthSellingInfo();
		
		int price = 0;
		for(SellingBean A : oneMonthList) {
			price += A.getSelling_price();
		}
		model.addAttribute("price", price);
		
		model.addAttribute("oneMonthList", oneMonthList);
		
		return "board/oneMonthTotal";
	}
	
	//날짜 조회해서 통계내기 
	@RequestMapping("/Total")
	public String Total(@ModelAttribute("DateTotal") DayCommand DateTotal, Model model) {
		
		if(DateTotal.getStart() != null && DateTotal.getEnd() != null) {
			List<SellingBean> DayTotal = boardService.getTotalInfo(DateTotal.getStart(), DateTotal.getEnd());
			
			int price = 0;
			for(SellingBean A : DayTotal) {
				price += A.getSelling_price();
			}
			
			model.addAttribute("price", price);
			
			model.addAttribute("DayTotal", DayTotal);
		}
		
		return "board/Total";
	}
	
	
}