package com.system.pos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.system.pos.beans.ContentBean;

public class BoardMapper implements RowMapper<ContentBean> {
	
	@Override
	public ContentBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		ContentBean bean = new ContentBean();
		bean.setContent_idx(rs.getInt("content_idx"));
		bean.setContent_date(rs.getString("content_date"));
		bean.setContent_name(rs.getString("content_name"));
		bean.setContent_price(rs.getInt("content_price"));;
		bean.setContent_count(rs.getInt("content_count"));
		
		return bean;
	}
}