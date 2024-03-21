package com.system.pos.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.system.pos.beans.UserBean;
import com.system.pos.interceptor.CheckLoginInterceptor;
import com.system.pos.interceptor.TopMenuInterceptor;
import com.system.pos.service.TopMenuService;

@Configuration //스프링 MVC 관련 설정 클래스 
public class ServletAppContext implements WebMvcConfigurer{

	@Autowired
	private TopMenuService topMenuService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean; //여기서 사용할 거라 로그인 정보 갖는 객체 필드로 주입해놓고 
	
	//인터셉터 등록하기 - 재정의
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		//메뉴리스트 
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService);
		
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		reg1.addPathPatterns("/**");
		
		//로그인 
		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/**" ); //이 경로에 거칠 경우 인터셉터 거치도록함
		//reg2.excludePathPatterns("menu/main"); //이 경로는 제외하고 인터셉터 겨치도록함
	}
	
}
