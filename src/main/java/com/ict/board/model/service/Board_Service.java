package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import com.ict.board.model.vo.Board_VO;

public interface Board_Service {

	public int getTotalCount() ;
	
	public List<Board_VO> getBoardList(int begin, int end);
	
	public Board_VO getBoardOneList(String idx);
	
	public int getBoardHit(String idx);
	
	public int getBoardInsert(Board_VO board_VO);

	public int getLevUp(Map<String, Integer> map);
	
	public int getBoardAnsInsert(Board_VO board_VO);
	
	public int getBoardDelete(String idx);
	
	// 연습
	public int getAfter_Lev(Board_VO board_VO);
}
