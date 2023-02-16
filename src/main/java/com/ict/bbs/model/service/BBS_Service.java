package com.ict.bbs.model.service;

import java.util.List;

import com.ict.bbs.model.vo.BBS_VO;

public interface BBS_Service {
	
	List<BBS_VO> getList();
	
	BBS_VO getOneList(String b_idx);
	
	int getInsert(BBS_VO bvo);
	
	int getDelete(String b_idx);
	
	int getUpdate(BBS_VO bvo);
}
