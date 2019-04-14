package com.testboard.biz.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.testboard.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.testboard.biz..*Impl.get*(..))")
	public void getPointcut() {}
}
