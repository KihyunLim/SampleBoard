package com.testboard.biz.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testboard.biz.board.BoardVO;
import com.testboard.biz.common.paging.Criteria;

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
	
	public List<BoardVO> getBoardList(Criteria cri, String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageStart", cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		param.put("condition", condition);
		param.put("keyword", "%" + keyword + "%");
		
		return mybatis.selectList("BoardDAO.getBoardList", param); 
	}
	
	public int getBoardListCount(Criteria cri, String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageStart", cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		param.put("condition", condition);
		param.put("keyword", "%" + keyword + "%");
		
		return mybatis.selectOne("BoardDAO.getBoardListCount", param);
	}
}
