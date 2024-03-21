package com.system.pos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.system.pos.beans.SellingBean;

public class SellingInfoMapper implements RowMapper<SellingBean> {
	
	@Override
	public SellingBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		SellingBean bean = new SellingBean();

		bean.setSelling_idx(rs.getInt("selling_idx"));
		bean.setSelling_date(rs.getString("selling_date"));
		bean.setSelling_name(rs.getString("selling_name"));
		bean.setSelling_price(rs.getInt("selling_price"));
		
		return bean;
	}
	
}