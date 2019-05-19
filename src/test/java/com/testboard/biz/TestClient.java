package com.testboard.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.testboard.biz.boardReply.BoardReplyService;

public class TestClient {

	public static void main(String[] args) {

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
//		BoardReplyService
	}
}
