package com.testboard.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testboard.biz.user.UserService;
import com.testboard.biz.user.UserVO;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		return "login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		if(vo.getUserId() == null || vo.getUserId().equals("")) {
			throw new IllegalArgumentException("아이디를 입력해주세요.");
		} else if(vo.getPassword() == null || vo.getPassword().equals("")) {
			throw new IllegalArgumentException("비밀번호를 입력해주세요.");
		}
		
		UserVO user = userService.getUserInfo(vo);
		if(user != null) {
			session.setAttribute("name", vo.getName());
			session.setAttribute("userId", vo.getUserId());
			
			return "redirect:getBoardList.do";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "login";
	}
}