package com.testboard.controller.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Controller
public class DeleteBoardController {

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
			System.out.println(e.getMessage());
			
			result.put("result",  false);
			result.put("message", "게시글 삭제에 실패했습니다.");
		}
		
		return result;
	}
}
