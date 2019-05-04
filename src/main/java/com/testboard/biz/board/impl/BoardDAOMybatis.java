package com.testboard.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testboard.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertBoard(BoardVO vo) {
		return mybatis.insert("BoardDAO.insertBoard", vo);
	}
	
	public void updateBoard(BoardVO vo) {
		
	}
	
	public void deleteBoard(BoardVO vo) {
		
	}
	
	public void updateBoardCnt(BoardVO vo) {
		
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		return mybatis.selectList("BoardDAO.getBoardList", vo); 
	}
	
	public int getBoardListCount(BoardVO vo) {
		return 1;
	}
}
