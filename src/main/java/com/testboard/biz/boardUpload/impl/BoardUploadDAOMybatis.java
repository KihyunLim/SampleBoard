package com.testboard.biz.boardUpload.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<String> getBoardFiles(Integer boardSeq) throws Exception {
		return mybatis.selectList("BoardUploadDAO.getBoardFiles", boardSeq);
	}
	
	public void deleteFiles(Integer boardSeq) throws Exception {
		mybatis.delete("BoardUploadDAO.deleteFiles", boardSeq);
	}
	
	public int getBoardFileCnt(Integer boardSeq) throws Exception {
		return mybatis.selectOne("BoardUploadDAO.getBoardFileCnt", boardSeq);
	}
	
	public void replaceFile(String fileName, Integer boardSeq) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fileName", fileName);
		paramMap.put("boardSeq", boardSeq);
		
		mybatis.insert("BoardUploadDAO.replaceFile", paramMap);
	}
}
