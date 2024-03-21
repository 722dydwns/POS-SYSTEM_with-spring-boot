package com.system.pos.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.system.pos.beans.UserBean;

@Mapper
public interface UserDao {
	// 회원가입 기능
	// 1) id 중복 겁사 위한 쿼리문
	@Select("SELECT user_name FROM user_table WHERE user_id = #{user_id}")
	String checkUserIdExist(String user_id);

	// 2) 가입한 회원 DB 삽입 쿼리문
	@Insert("INSERT INTO user_table (user_name, user_id, user_pw) VALUES ( #{user_name},#{user_id},#{user_pw} )")
	void addUserInfo(UserBean joinUserBean);

	// 로그인 기능 - 사용자가 입력한 id,pw 정보기준으로 name select하여 UserBean 객체로 반환하는 쿼리문
	@Select("SELECT user_name FROM user_table WHERE user_id = #{user_id} AND user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);

	// 정보수정 시 - 현재 로그인한 사용자의 id 값 기준으로 로그인 사용자 정보 일부를 가져옴
	@Select("SELECT user_id, user_name FROM user_table WHERE user_id = #{user_id}")
	UserBean getModifyUserInfo(String user_id);

	// 정보수정 처리
	@Update("UPDATE user_table SET user_pw = #{user_pw} WHERE user_id = #{user_id}")
	void modifyUserInfo(UserBean modifyUserBean);

}
