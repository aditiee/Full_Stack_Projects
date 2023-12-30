package com.spring.mvc.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.jdbc.dao.UserDetailDao;
import com.spring.mvc.jdbc.model.UserDetail;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDetailDao userDetailDao;

	public UserDetail getUserDetail(long id) {
		return userDetailDao.getUserDetail(id);
	}

	public List<UserDetail> getAllUserDetail() {
		return userDetailDao.getAllUserDetail();
	}

	public UserDetailDao getUserDetailDao() {
		return userDetailDao;
	}

	public int addUserDetail(UserDetail userDetail) {
		
		return userDetailDao.addUserDetail(userDetail);
	}

	public int updateUserDetail(UserDetail userDetail) {
		
		return userDetailDao.updateUserDetail(userDetail);
	}

	public int deleteUserDetail(long id) {
		
		return userDetailDao.deleteUserDetail(id);
	}
}