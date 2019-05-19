package com.testboard.biz.boardReply.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testboard.biz.boardReply.BoardReplyVO;

@Repository
public class BoardReplyDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoardReply(BoardReplyVO vo) {
		mybatis.insert("BoardDAO.insertBoardReply", vo);
	}
	
	public void updateBoardReply(BoardReplyVO vo) {
		mybatis.update("BoardDAO.updateBoardReply", vo);
	}
	
	public void deleteBoardReply(BoardReplyVO vo) {
		mybatis.delete("BoardDAO.deleteBoardReply", vo);
	}
	
	public List<BoardReplyVO> getBoardReplyList(BoardReplyVO vo) {
		return mybatis.selectList("BoardDAO.getBoardReplyList", vo);
	}
	
	public int getBoardReplyListCount(BoardReplyVO vo) {
		return mybatis.selectOne("BoardDAO.getBoardReplyListCount", vo);
	}
}
