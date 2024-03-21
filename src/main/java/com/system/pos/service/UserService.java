package com.system.pos.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.pos.beans.UserBean;
import com.system.pos.dao.UserDao;
@Service
public class UserService {
	
	//매퍼 주입받고 
	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean; //로그인 사용자 정보 빈
	
	//아이디 중복 확인 체크
	public boolean checkUserIdExist(String user_id) {
		//들어온 id 값과 일치하는 데이터 존재여부를 매퍼로 접근해서 가져옴
		String user_name = userDao.checkUserIdExist(user_id);
		
		//DB 상에 해당 데이터 없다면 = 중복X
		if(user_name == null) {
			return true; //T  ㄱㅊ
		}else { //DB 상에 데이터 이미 존재하면 중복 O 
			return false; //F ㄴㄴ
		}
	}
	//가입한 회원정보 DB에 저장
	public void addUserInfo(UserBean joinUserBean) {
		if (checkUserIdExist(joinUserBean.getUser_id()) == false) { //중복될 경우
			throw new RuntimeException("중복된 아이디로 회원가입 불가");
		}
		userDao.addUserInfo(joinUserBean);
	}
	
	//로그인 성공 
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2 != null) { //잘 등록된 User에 한해서
			//sesseionScope 에 정의된 loginUserBean 정보 세팅 (로그인 User - id/name/login 상태)
			loginUserBean.setUser_id(tempLoginUserBean.getUser_id());
			loginUserBean.setUser_name(tempLoginUserBean.getUser_name());
			loginUserBean.setUserLogin(true); //로그인 O 상태 처리
		}
	}
	
	//정보수정할 현재 로그인 객체를 받아서 매퍼에서 해당 객체의 id와 일치하는 User 객체 얻어오고 일부를 세팅
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean = userDao.getModifyUserInfo(loginUserBean.getUser_id());
		
		//수정 세팅
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_idx(tempModifyUserBean.getUser_idx()); //수정할 객체의 idx를 매퍼의 DB 객체 일치시켜줌
	}
	
	//DB에 수정 처리 - 현재 로그인 사용자의 idx 값을 얻어서 일치시켜주고 
	public void modifyUserInfo(UserBean modifyUserBean) {
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		userDao.modifyUserInfo(modifyUserBean); //업데이트 처리 
	}
	
}
