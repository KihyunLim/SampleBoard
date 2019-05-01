package com.testboard.biz.common.customException;

@SuppressWarnings("serial")
public class CommonException extends Exception {

	CommonException() {
		super();
	}
	
	public CommonException(String message) {
		super(message);
	}
}
