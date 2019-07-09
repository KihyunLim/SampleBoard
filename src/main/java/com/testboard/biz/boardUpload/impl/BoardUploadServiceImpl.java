package com.testboard.biz.boardUpload.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testboard.biz.boardUpload.BoardUploadService;

@Service("boardUploadService")
public class BoardUploadServiceImpl implements BoardUploadService {

	@Autowired
	private BoardUploadDAOMybatis boardUploadDAO;
	
	public void addFile(String fileName) throws Exception {
		boardUploadDAO.addFile(fileName);
	}
	
	public List<String> getBoardFiles(Integer boardSeq) throws Exception {
		return boardUploadDAO.getBoardFiles(boardSeq);
	}
	
	public void deleteFiles(Integer boardSeq) throws Exception {
		boardUploadDAO.deleteFiles(boardSeq);
	}
	
	public int getBoardFileCnt(Integer boardSeq) throws Exception {
		return boardUploadDAO.getBoardFileCnt(boardSeq);
	}
}