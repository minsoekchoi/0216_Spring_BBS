package com.ict.shop.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.shop.model.vo.Shop_VO;

// Repositoty의 의미는?? 저장소
// 쓰임은??
@Repository
public class Shop_DAO {
	// Autowired의 뜻은??
	// Spring에서 Bean 객체를 주입받기 위한 방법중 하나
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<Shop_VO> getShopList(String category) {
		return sqlSessionTemplate.selectList("shop.list", category);
	}
	
	public Shop_VO getShopOneList(String idx) {
		return sqlSessionTemplate.selectOne("shop.onelist" ,idx);
	}
	
	public int getMemberRegister(Shop_VO shop_VO) {
		return sqlSessionTemplate.insert("shop.register", shop_VO);
	}
	
	public Shop_VO getMemberPW_Chk(String m_id) {
		return sqlSessionTemplate.selectOne("shop.memberpwchk", m_id);
	}
	
	public int getProductInsert(Shop_VO shop_VO) {
		return sqlSessionTemplate.insert("shop.productinsert", shop_VO);
	}
	
	public int getMyStoreSaveInsert(List<Shop_VO> clist) {
		return sqlSessionTemplate.insert("shop.mystoreinsert", clist);
	}
}