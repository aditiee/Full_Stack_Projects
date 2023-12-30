package com.spring.mvc.jdbc.service;

import java.util.List;

import com.spring.mvc.jdbc.model.UserDetail;

public interface UserDetailService {
	public UserDetail getUserDetail(long id);
	public List<UserDetail> getAllUserDetail();
	public int addUserDetail(UserDetail userDetail);
	public int updateUserDetail(UserDetail userDetail);
	public int deleteUserDetail(long id);
}