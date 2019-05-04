package com.testboard.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class ListController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		return "getBoardList";
	}
	
	@RequestMapping(value="/getBoardListJSON.do")
	@ResponseBody
	public List<BoardVO> getBoardListJSON(BoardVO vo) {
		return boardService.getBoardList(vo); 
	}
}
