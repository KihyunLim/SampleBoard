package com.testboard.biz.board;

import java.util.List;

public interface BoardService {

	int insertBoard(BoardVO vo);

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	void updateBoardCnt(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(BoardVO vo);

	int getBoardListCount(BoardVO vo);

}