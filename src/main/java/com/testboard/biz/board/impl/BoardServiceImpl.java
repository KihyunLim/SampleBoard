package com.testboard.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;
import com.testboard.biz.boardUpload.BoardUploadService;
import com.testboard.biz.common.paging.Criteria;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAOMybatis boardDAO;
	
	@Autowired
	private BoardUploadService boardUploadService;
	
	@Transactional
	public void insertBoard(BoardVO vo) throws Exception {
		boardDAO.insertBoard(vo);
		String[] files = vo.getFiles();
		
		if(files != null) {
			for(String fileName : files) {
//				boardUploadService.addFile(fileName);
				boardUploadService.addFile(fileName);
			}
		}
	}
	
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	public void updateBoardCnt(BoardVO vo) {
		boardDAO.updateBoardCnt(vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	public List<BoardVO> getBoardList(Criteria cri, String condition, String keyword) {
		return boardDAO.getBoardList(cri, condition, keyword);
	}
	
	public int getBoardListCount(Criteria cri, String condition, String keyword) {
		return boardDAO.getBoardListCount(cri, condition, keyword);
	}
}
