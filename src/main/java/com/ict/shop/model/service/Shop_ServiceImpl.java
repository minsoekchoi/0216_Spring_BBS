package com.ict.shop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.model.dao.Shop_DAO;
import com.ict.shop.model.vo.Shop_VO;

// implements와 extends의 차이는??
// implements	: 부모의 특징을 도구로 사용해 새로운 특징을 만들어 사용, 부모 객체는 선언만 하며 정의는 반드시 오버라이딩해서 사용한다.
// extends		: 부모에서 선언/정의를 모두하며 자식은 오버라이딩 할 필요 없이 부모의 메소드 변수를 그대로 사용할 수 있다.
@Service
public class Shop_ServiceImpl implements Shop_Service {
	@Autowired
	private Shop_DAO shop_DAO;

	public void setShop_DAO(Shop_DAO shop_DAO) {
		this.shop_DAO = shop_DAO;
	}

	@Override
	public List<Shop_VO> getShopList(String category) throws Exception {
		return shop_DAO.getShopList(category);
	}

	@Override
	public Shop_VO getShopOneList(String idx) throws Exception {
		return shop_DAO.getShopOneList(idx);
	}
	
	@Override
	public int getMemberRegister(Shop_VO shop_VO) throws Exception {
		return shop_DAO.getMemberRegister(shop_VO);
	}
	
	@Override
	public Shop_VO getMemberPW_Chk(String m_id) throws Exception {
		return shop_DAO.getMemberPW_Chk(m_id);
	}
	
	@Override
	public int getProductInsert(Shop_VO shop_VO) {
		return shop_DAO.getProductInsert(shop_VO);
	}
	
	@Override
	public int getMyStoreSaveInsert(List<Shop_VO> clist) {
		return shop_DAO.getMyStoreSaveInsert(clist);
	}
}
