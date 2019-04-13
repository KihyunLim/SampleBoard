package com.testboard.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class ListController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		model.addAttribute("boardList", boardService.getBoardList(vo));
		
		return "getBoardList";
	}
}
