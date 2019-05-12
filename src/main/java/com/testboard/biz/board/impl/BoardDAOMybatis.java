package com.testboard.biz.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testboard.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard", vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	
	public void updateBoardCnt(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoardCnt", vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo, String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("vo", vo);
		param.put("condition", condition);
		param.put("keyword", "%" + keyword + "%");
		
		return mybatis.selectList("BoardDAO.getBoardList", param); 
	}
	
	public int getBoardListCount(BoardVO vo) {
		return 1;
	}
}
