package com.testboard.biz.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		
		if(obj == null) {
			LOGGER.info(">>>>> 로그인 안하고 시도하는 중!!");
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("var confirmMoveLogin = confirm('로그인 후 이용가능합니다. \n로그인 화면으로 이동하시겠습니까?');");
				out.println("if(confirmMoveLogin) {");
				out.println("	location.href = '/SampleBoard/login.do';");
				out.println("} else {");
				out.println("	history.go(-1);");
				out.println("}");
				out.println("</script>");
				out.flush();
			} catch(Exception e) {
				LOGGER.error("error message : " + e.getMessage());
				LOGGER.error("error trace : ", e);
			}

			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}