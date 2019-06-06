package com.testboard.biz.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(AfterThrowingAdvice.class);

	@AfterThrowing(pointcut="PointcutCommon.allPointcut()", throwing="exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String method = jp.getSignature().getName();
		LOGGER.info("[AfterThrowing] " + method + "() 비즈니스 로직 수행 중 예외 발생!");
		
		if(exceptObj instanceof IllegalAccessException) {
			LOGGER.info("부적합한 값이 입력되었습니다.");
		} else if(exceptObj instanceof NumberFormatException) {
			LOGGER.info("숫자 형식의 값이 아닙니다.");
		} else if(exceptObj instanceof NullPointerException) {
			LOGGER.info("널널하네요ㅠㅠ");
		} else if(exceptObj instanceof Exception) {
			LOGGER.info("문제가 발생했습니다.");
		}
		
		LOGGER.info(exceptObj.getMessage());
//		exceptObj.printStackTrace();
	}
}
