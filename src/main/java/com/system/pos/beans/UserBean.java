package com.system.pos.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean { //사용자 데이터 정보 객체
	// 1) DB 속 데이터 구조와 동일하게 필드 넣음
	private int user_idx; // 얘는 자동 increment 로 선언해놔서 ㄱㅊ

	// 유효성 검사 @Size @Pattern
	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*") // 한글만 입력가능
	private String user_name;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*") // 영어 대/소문자와 숫자만 입력가능
	private String user_id;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw2;

	// 2) 중복 여부 필드 F,T -> 추후 이 값은 jsp 파일 속 jQuery 문에서 T/F 설정됨
	// 사용자가 form에 입력한 값의 중복 여부를 판단하기 위함
	private boolean userIdExist; // 중복검사 필드

	// 3) 로그인 여부 판단해줄 필드
	private boolean userLogin;

	public UserBean() { // 생성자에서 초기값 세팅
		this.userIdExist = false; // 일단 초기값은 false 값주기
		this.userLogin = false; // 얘도 초기값 false
	}

	// get set
	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_pw2() {
		return user_pw2;
	}

	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}

	public boolean isUserIdExist() {
		return userIdExist;
	}

	public void setUserIdExist(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

}
