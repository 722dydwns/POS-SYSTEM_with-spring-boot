package com.system.pos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main"; //기본 경로 -> /main 으로 ReDirect 처리 
	}
	
}