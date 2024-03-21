package com.system.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.system.pos.beans.BoardInfoBean;

@Mapper
public interface TopMenuDao {

	@Select("SELECT board_info_idx, board_info_name FROM board_info_table ORDER BY board_info_idx ")
	List<BoardInfoBean> getTopMenuList();
}

