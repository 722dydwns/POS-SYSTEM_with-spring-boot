package com.system.pos.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.system.pos.beans.UserBean;

public class UserValidator implements Validator{ //User 유효성 검사 객체
 
	//재정의
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz); //UserBean 객체에 대한 유효성 검사
	}
	
	@Override
	// 실질적인 유효성 검사 메소드  
	//@Valid 주입 시 모두 UserBean 객체로 동일하기 때문에 각각 매핑된 객체 이름 얻어서 
	//다시 분기하여 유효성 검사처리를 해주어야 코드가 안꼬인다. 
	// ex 회원가입할 User인지, 로그인할 User인지 구분하기 위함
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) target; //target 으로 들어온 객체를 UserBean으로 형변환 
		
		String beanName = errors.getObjectName(); //bean 일름 얻기
		
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) { //유효성 검사하는 빈이 가입 빈이면
			//만약 두 패스워드 값이 다를 경우 
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {//두 비번 다르면 에러
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw", "NotEquals");
				//error 메시지 띄움
			}
		}
	}

}
