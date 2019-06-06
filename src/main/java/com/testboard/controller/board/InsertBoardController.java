package com.testboard.controller.board;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class InsertBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsertBoardController.class);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView(BoardVO vo) {
		return "insertBoard";
	}
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertBoard(BoardVO vo) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(vo.getTitle() == null || vo.getTitle().equals("")) {
			result.put("result", false);
			result.put("message", "제목을 입력하세요");
			
			return result;
		} else if(vo.getContent() == null || vo.getContent().equals("")) {
			result.put("result", false);
			result.put("message", "내용을 입력하세요.");
			
			return result;
		}
		
		try {
			boardService.insertBoard(vo);
			
			result.put("result", true);
			result.put("message", "게시글 등록에 성공했습니다.");
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			
			result.put("result", false);
			result.put("message", "게시글 등록에 실패했습니다.");
		}
		
		return result;
	}
}
