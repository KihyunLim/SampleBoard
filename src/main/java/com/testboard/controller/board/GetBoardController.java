package com.testboard.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class GetBoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		return "getBoard";
	}
	
	@RequestMapping("/getBoardJSON.do")
	@ResponseBody
	public BoardVO getBoardJSON(BoardVO vo) {
		return boardService.getBoard(vo);
	}
}