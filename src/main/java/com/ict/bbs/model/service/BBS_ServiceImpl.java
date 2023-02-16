package com.ict.bbs.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.bbs.model.dao.BBS_DAO;
import com.ict.bbs.model.vo.BBS_VO;

@Service
public class BBS_ServiceImpl implements BBS_Service{
	@Autowired
	private BBS_DAO bbsDAO;
	
	public void setBbsDAO(BBS_DAO bbsDAO) {
		this.bbsDAO = bbsDAO;
	}

	@Override
	public List<BBS_VO> getList() {
		return bbsDAO.getList();
	}
	
	@Override
	public BBS_VO getOneList(String b_idx) {
		return bbsDAO.getOneList(b_idx);
	}
	
	@Override
	public int getInsert(BBS_VO bvo) {
		return bbsDAO.getInsert(bvo);
	}
	
	@Override
	public int getDelete(String b_idx) {
		return bbsDAO.getDelete(b_idx);
	}
	
	@Override
	public int getUpdate(BBS_VO bvo) {
		return bbsDAO.getUpdate(bvo);
	}
}
