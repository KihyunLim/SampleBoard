package com.testboard.biz.boardReply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testboard.biz.boardReply.BoardReplyService;
import com.testboard.biz.boardReply.BoardReplyVO;

@Service("boardReplyService")
public class BoardReplyServiceImpl implements BoardReplyService {

	@Autowired
	private BoardReplyDAOMybatis boardReplyDAO;
	
	public void insertBoardReply(BoardReplyVO vo) {
		boardReplyDAO.insertBoardReply(vo);
	};

	public void updateBoardReply(BoardReplyVO vo) {
		boardReplyDAO.updateBoardReply(vo);
	};

	public void deleteBoardReply(BoardReplyVO vo) {
		boardReplyDAO.deleteBoardReply(vo);
	};

	public List<BoardReplyVO> getBoardReplyList(BoardReplyVO vo) {
		return boardReplyDAO.getBoardReplyList(vo);
	};

	public int getBoardReplyListCount(BoardReplyVO vo) {
		return boardReplyDAO.getBoardReplyListCount(vo);
	};
}