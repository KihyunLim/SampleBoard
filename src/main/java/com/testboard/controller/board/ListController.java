package com.testboard.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;
import com.testboard.biz.common.paging.Criteria;
import com.testboard.biz.common.paging.PageMaker;

@Controller
public class ListController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		return "getBoardList";
	}
	
	@RequestMapping(value="/getBoardListJSON.do")
	@ResponseBody
	public Map<String, Object> getBoardListJSON(Criteria cri, 
			@RequestParam(value="searchCondition", defaultValue="ALL", required=false) String condition,
			@RequestParam(value="searchKeyword", required=false) String keyword
			) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		LOGGER.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 테스트요오 debug : " + condition);
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 테스트요오 info : " + condition);
		LOGGER.warn(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 테스트요오 warn : " + condition);
		LOGGER.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 테스트요오 error : " + condition);
		
		try {
			List<BoardVO> boardList = boardService.getBoardList(cri, condition, keyword);
			result.put("boardList", boardList);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(boardService.getBoardListCount(cri, condition, keyword));
			result.put("pageMaker", pageMaker);
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			
			result.put("message", "게시글 조회 실패");
		}
		
		return result;
	}
}
