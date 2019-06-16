package com.testboard.biz.boardUpload.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardUploadDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void addFile(String fileName) throws Exception {
		mybatis.insert("BoardUploadDAO.addFile", fileName);
	}
}
