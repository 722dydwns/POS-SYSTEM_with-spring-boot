package com.system.pos.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.system.pos.beans.BoardInfoBean;
import com.system.pos.service.TopMenuService;
import org.springframework.web.servlet.ModelAndView;

public class TopMenuInterceptor implements HandlerInterceptor{

	private TopMenuService topMenuService;
	
	public TopMenuInterceptor(TopMenuService topMenuService) {
		this.topMenuService = topMenuService;
	}
	
	//인터셉터로 등록 -> 메뉴는 어떤 페이지에서도 동작해야 하므로 인터셉터로 등록하면 됨 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		
		return true;
	}

}
