package com.testboard.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class GetBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetBoardController.class);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		return "getBoard";
	}
	
	@RequestMapping("/getBoardJSON.do")
	@ResponseBody
	public BoardVO getBoardJSON(BoardVO vo) {
		BoardVO result = boardService.getBoard(vo);
		
		try {
			boardService.updateBoardCnt(vo);
			result.setCnt(result.getCnt() + 1);
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return result;
	}
}