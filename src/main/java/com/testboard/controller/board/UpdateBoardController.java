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
public class UpdateBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateBoardController.class);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/updateBoard.do")
	@ResponseBody
	public Map<String, Object> updateBoard(BoardVO vo) {
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
			boardService.updateBoard(vo);
			
			result.put("result", true);
			result.put("message", "게시글 수정에 성공했습니다.");
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			result.put("result", false);
			result.put("message", "게시글 수정에 실패했습니다.");
		}
		
		return result;
	}
}
