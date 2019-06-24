package com.testboard.controller.boardUpload;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER .error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/fileDelete", method=RequestMethod.POST)
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
}
