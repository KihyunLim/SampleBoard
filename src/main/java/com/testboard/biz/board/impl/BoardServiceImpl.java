package com.testboard.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testboard.biz.board.BoardService;
import com.testboard.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAOMybatis boardDAO;
	
	public int insertBoard(BoardVO vo) {
		return boardDAO.insertBoard(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		
	}
	
	public void deleteBoard(BoardVO vo) {
		
	}
	
	public int updateBoardCnt(BoardVO vo) {
		return boardDAO.updateBoardCnt(vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
	
	public int getBoardListCount(BoardVO vo) {
		return 1;
	}
}
