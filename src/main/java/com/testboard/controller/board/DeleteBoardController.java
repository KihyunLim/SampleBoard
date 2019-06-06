package com.testboard.controller.board;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class DeleteBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteBoardController.class);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/deleteBoard.do")
	@ResponseBody
	public Map<String, Object> deleteBoard(BoardVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			boardService.deleteBoard(vo);
			
			result.put("result", true);
			result.put("message", "게시글이 삭제 되었습니다.");
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			result.put("result",  false);
			result.put("message", "게시글 삭제에 실패했습니다.");
		}
		
		return result;
	}
}
