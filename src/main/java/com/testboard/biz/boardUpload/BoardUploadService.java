package com.testboard.biz.boardUpload;

import java.util.List;

public interface BoardUploadService {

	void addFile(String fileName) throws Exception;

	List<String> getBoardFiles(Integer boardSeq) throws Exception;
	
	void deleteFiles(Integer boardSeq) throws Exception;
	
	int getBoardFileCnt(Integer boardSeq) throws Exception;
}