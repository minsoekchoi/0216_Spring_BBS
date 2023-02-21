package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.model.dao.Board_DAO;
import com.ict.board.model.vo.Board_VO;

@Service
public class Borad_ServiceImpl implements Board_Service {

	@Autowired
	private Board_DAO board_DAO;

	public void setBoard_DAO(Board_DAO board_DAO) {
		this.board_DAO = board_DAO;
	}

	@Override
	public int getTotalCount() {
		return board_DAO.getTotalCount();
	}

	@Override
	public List<Board_VO> getBoardList(int begin, int end) {
		return board_DAO.getBoardList(begin, end);
	}

	@Override
	public int getBoardInsert(Board_VO board_VO) {
		return board_DAO.getBoardInsert(board_VO);
	}

	@Override
	public int getBoardHit(String idx) {
		return board_DAO.getBoardHit(idx);
	}

	@Override
	public Board_VO getBoardOneList(String idx) {
		return board_DAO.getBoardOneList(idx);
	}

	@Override
	public int getLevUp(Map<String, Integer> map) {
		return board_DAO.getLevUp(map);
	}

	@Override
	public int getBoardAnsInsert(Board_VO board_VO) {
		return board_DAO.getBoardAnsInsert(board_VO);
	}

	@Override
	public int getBoardDelete(String idx) {
		return board_DAO.getBoardDelete(idx);
	}

	// 연습
	@Override
	public int getAfter_Lev(Board_VO board_VO) {
		return board_DAO.getAfter_Lev(board_VO);
	}
}
