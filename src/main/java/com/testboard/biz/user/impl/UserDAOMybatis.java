package com.testboard.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testboard.biz.user.UserVO;

@Repository
public class UserDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserVO getUserInfo(UserVO vo) {
		return (UserVO) mybatis.selectOne("UseresDAO.getUserInfo", vo); 
	}
}