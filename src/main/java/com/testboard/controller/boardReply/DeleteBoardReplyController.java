package com.testboard.controller.boardReply;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.boardReply.BoardReplyService;
import com.testboard.biz.boardReply.BoardReplyVO;

@Controller
public class DeleteBoardReplyController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteBoardReplyController.class);

	@Autowired
	private BoardReplyService boardReplyService;
	
	@RequestMapping("/deleteBoardReply.do")
	@ResponseBody
	public Map<String, Object> deleteBoardReply(BoardReplyVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			boardReplyService.deleteBoardReply(vo);
			
			result.put("result", true);
			result.put("message", "댓글이 삭제되었습니다.");
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			
			result.put("result", false);
			result.put("message", "댓글 삭제에 실패했습니다.");
		}
		
		return result;
	}
}