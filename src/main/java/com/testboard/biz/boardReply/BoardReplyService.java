package com.testboard.biz.boardReply;

import java.util.List;

public interface BoardReplyService {

	void insertBoardReply(BoardReplyVO vo);

	void updateBoardReply(BoardReplyVO vo);

	void deleteBoardReply(BoardReplyVO vo);

	List<BoardReplyVO> getBoardReplyList(BoardReplyVO vo);

	int getBoardReplyListCount(BoardReplyVO vo);

}