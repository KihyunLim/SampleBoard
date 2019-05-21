package com.testboard.controller.boardReply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.boardReply.BoardReplyService;
import com.testboard.biz.boardReply.BoardReplyVO;

@Controller
public class GetBoardReplyListController {

	@Autowired
	private BoardReplyService boardReplyService;
	
	@RequestMapping("/getBoardReplyList.do")
	@ResponseBody
	public Map<String, Object> getBoardReplyList(BoardReplyVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			List<BoardReplyVO> boardReplyList = boardReplyService.getBoardReplyList(vo);
			result.put("boardReplyList", boardReplyList);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			
			result.put("message", "댓글 조회 실패");
		}
		
		return result;
	}
}
