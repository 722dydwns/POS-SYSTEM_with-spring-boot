package com.system.pos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.system.pos.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{
	//인터셉터의 경우 필드로 바로 주입 못받고, 생성자로 받기
	private UserBean loginUserBean;
	
	//생성자
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean =loginUserBean;
	}
	
	//재정의 - 로그인 여부에 따라 인터셉터로 접근 권한 설정함
	//preHandle = 컨트롤러 실행 전 수행한다.
	//반환 값이 true일 경우 컨트롤러로 진입하고 false일 경우 진입하지 않는다. Object handler는 진입하려는 컨트롤러의 클래스 객체가 담겨있다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if(loginUserBean.isUserLogin() == false) { //로그인 X객체
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login"); //경로 리다이렉팅
			return false; //인터셉터에서 F 반환 시 여기서 종료하게 됨
		}
		return true; //인터셉터에서 T 반환 시 다음 단계로 넘어갈 수 있음
	}
	
	
}
