package com.spring.mvc.jdbc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.jdbc.model.UserDetail;
import com.spring.mvc.jdbc.rowmapper.UserDetailRowMapper;


@Repository
public class UserDetailDaoImpl implements UserDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public UserDetail getUserDetail(long id) {
		UserDetail userDetail = (UserDetail) jdbcTemplate.queryForObject("select * from user_detail where id = ?",new Object[] { id }, new UserDetailRowMapper());
		return userDetail;
	}
	@Transactional
	public List<UserDetail> getAllUserDetail() {
		List<UserDetail> userDetail = (List<UserDetail>) jdbcTemplate.query("select * from user_detail",
				new UserDetailRowMapper());
		return userDetail;
	}
	@Transactional
	public int addUserDetail(UserDetail userDetail) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("user_detail").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<String, Object>(4);
		parameters.put("first_name", userDetail.getFirstName());
		parameters.put("last_name", userDetail.getLastName());
		parameters.put("email", userDetail.getEmail());
		parameters.put("contactNo", userDetail.getContactNo());
		Number insertedId = simpleJdbcInsert.executeAndReturnKey(parameters);
		return insertedId.intValue();
	}
	@Transactional
	public int updateUserDetail(UserDetail userDetail) {
		String sql = "update user_detail set first_name = ?, last_name = ?, email = ?, contactNo = ? where id = ?";
		int resp = jdbcTemplate.update(sql, new Object[] { userDetail.getFirstName(), userDetail.getLastName(),
				userDetail.getEmail(), userDetail.getContactNo(), userDetail.getId() });
		return resp;
	}
	@Transactional
	public int deleteUserDetail(long id) {
		int resp = jdbcTemplate.update("delete from user_detail where id = ?", id);
		return resp;
	}
}