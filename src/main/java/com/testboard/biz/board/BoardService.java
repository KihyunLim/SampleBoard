package com.testboard.biz.board;

import java.util.List;

public interface BoardService {

	void insertBoard(BoardVO vo);

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	void updateBoardCnt(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(BoardVO vo, String condition, String keyword);

	int getBoardListCount(BoardVO vo);

}