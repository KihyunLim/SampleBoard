package com.testboard.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.user.UserService;
import com.testboard.biz.user.UserVO;

@Controller
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		return "login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(UserVO vo, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(vo.getUserId() == null || vo.getUserId().equals("")) {
			result.put("result", false);
			result.put("message", "아이디를 입력해주세요.");
			
			return result;
		} else if(vo.getPassword() == null || vo.getPassword().equals("")) {
			result.put("result", false);
			result.put("message", "비밀번호를 입력해주세요.");
			
			return result;
		}
		
		try {
			UserVO user = userService.getUserInfo(vo);
			
			if(user == null) {
				throw new NullPointerException("User inforamtion not found!!"); 
			}
			
			if(vo.getPassword().equals(user.getPassword())) {
				session.setAttribute("name", vo.getName());
				session.setAttribute("userId", vo.getUserId());
				
				result.put("result", true);
				result.put("message", "");
			} else {
				result.put("result", false);
				result.put("message", "비밀번호가 일지하지 않습니다.");
			}
		} catch(NullPointerException e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			result.put("result", false);
			result.put("message", "등록된 아이디가 없습니다.");
		}
		
		return result;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "login";
	}
}