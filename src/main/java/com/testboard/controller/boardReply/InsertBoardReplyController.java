package com.testboard.controller.boardReply;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.boardReply.BoardReplyService;
import com.testboard.biz.boardReply.BoardReplyVO;

@Controller
public class InsertBoardReplyController {

	@Autowired
	private BoardReplyService boardReplyService;
	
	@RequestMapping("/insertBoardReply.do")
	@ResponseBody
	public Map<String, Object> insertBoardReply(BoardReplyVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(vo.getContent() == null || vo.getContent().equals("")) {
			result.put("result", false);
			result.put("message",  "내용을 입력하세요.");
			
			return result;
		}
		
		try {
			boardReplyService.insertBoardReply(vo);
			
			result.put("result",  true);
			result.put("message",  "댓글이 등록되었습니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
			
			result.put("result", false);
			result.put("message", "댓글 등록에 실패했습니다.");
		}
		
		return result;
	}
}
