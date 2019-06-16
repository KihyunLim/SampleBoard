package com.testboard.controller.board;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;
import com.testboard.biz.boardUpload.BoardUploadService;

@Controller
public class InsertBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsertBoardController.class);

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardUploadService boardUploadService;
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView(BoardVO vo) {
		return "insertBoard";
	}
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
//	@Transactional		//어차피 처음에 실패할테니 테스트 겸사로 막아놓음. 성공해버리면 머..ㅎ
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
			String[] files = vo.getFiles();
			
			if(files != null) {
				for(String fileName : files) {
					boardUploadService.addFile(fileName);
				}
			}
			
			result.put("result", true);
			result.put("message", "게시글 등록에 성공했습니다.");
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			result.put("result", false);
			result.put("message", "게시글 등록에 실패했습니다.");
		}
		
		return result;
	}
}
