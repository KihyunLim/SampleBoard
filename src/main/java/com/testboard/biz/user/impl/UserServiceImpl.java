package com.testboard.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testboard.biz.user.UserService;
import com.testboard.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAOMybatis userDAO;
	
	public UserVO getUserInfo(UserVO vo) {
		return userDAO.getUserInfo(vo);
	}
}
