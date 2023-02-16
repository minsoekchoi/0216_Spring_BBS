package com.ict.bbs.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.bbs.model.vo.BBS_VO;

@Repository
public class BBS_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public List<BBS_VO> getList() {
		return sqlSessionTemplate.selectList("bbs.list");
	}
	
	public BBS_VO getOneList(String b_idx) {
		return sqlSessionTemplate.selectOne("bbs.onelist", b_idx);
	}
	
	public int getInsert(BBS_VO bvo) {
		return sqlSessionTemplate.insert("bbs.insert", bvo);
	}
	
	public int getDelete(String b_idx) {
		return sqlSessionTemplate.delete("bbs.delete", b_idx);
	}
	
	public int getUpdate(BBS_VO bvo) {
		return sqlSessionTemplate.update("bbs.update", bvo);
	}
}
