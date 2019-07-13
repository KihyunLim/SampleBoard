package com.testboard.controller.boardUpload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.testboard.biz.boardUpload.BoardUploadService;
import com.testboard.biz.common.fileUpload.UploadFileUtils;

@Controller
public class BoardUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardUploadController.class);
	
	@Autowired
	private BoardUploadService boardUploadService;
	
	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		
		try {
			String savedFilePath = UploadFileUtils.uploadFile(file, request);
			LOGGER.debug(">>>>>>>>>> uploadFile > savedFilePath : " + savedFilePath);
			
			entity = new ResponseEntity<>(savedFilePath, HttpStatus.CREATED);
		}catch (NullPointerException e) {
			LOGGER.error("error message : " + e.getMessage() + " 첨부파일 취소 눌럿을때만 이거 나오겠지??");
			
			entity = null;
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/fileDisplay.do", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception {
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
		String rootPath = UploadFileUtils.getRootPath(fileName, request);
		
		ResponseEntity<byte[]> entity = null;
		
		try(InputStream inputStream = new FileInputStream(rootPath + fileName)) {
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
		} catch(FileNotFoundException e) {
			LOGGER.warn("warn message : 파일 못찾았지만 예외문처리로 넘김 " + e.getMessage());
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER .error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/fileDelete.do", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		
		try {
			UploadFileUtils.deleteFile(fileName, request);
			entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER .error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//이 컨트롤러 클래스 자체부터 매핑 경로를 지정안해놧기 때문에 아래 주석처럼 사용 못함
//	@RequestMapping(value="/fileList/{boardSeq}", method=RequestMethod.GET)
	@RequestMapping(value="/getFileList.do", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getFiles(@RequestParam("boardSeq") Integer boardSeq) {
		ResponseEntity<List<String>> entity = null;
		
		LOGGER.debug(">>>>>>>>>> boardSeq : " + boardSeq);
		try {
			List<String> fileList = boardUploadService.getBoardFiles(boardSeq);
			entity = new ResponseEntity<>(fileList, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/deleteAll.do", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files, HttpServletRequest request) {
		
		if(files == null || files.length == 0) {
			return new ResponseEntity<>("DELETED", HttpStatus.OK);
		}
		
		ResponseEntity<String> entity = null;
		
		try {
			for(String fileName : files) {
				UploadFileUtils.deleteFile(fileName, request);
			}
			
			entity = new ResponseEntity<>("DELETD", HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : " , e);
			
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
