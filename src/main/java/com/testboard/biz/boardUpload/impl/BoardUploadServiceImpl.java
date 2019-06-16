package com.testboard.biz.boardUpload.impl;

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
}
