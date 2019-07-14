package com.testboard.biz.board;

import java.util.List;

import com.testboard.biz.common.paging.Criteria;

public interface BoardService {

	void insertBoard(BoardVO vo) throws Exception;

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	void updateBoardCnt(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(Criteria cri, String condition, String keyword);

	int getBoardListCount(Criteria cri, String condition, String keyword);

}