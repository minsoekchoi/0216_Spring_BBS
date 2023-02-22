package com.ict.shop.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ict.bbs.model.vo.BBS_VO;
import com.ict.shop.model.vo.Shop_VO;

@Service
public interface Shop_Service {
	// Exception을 제대로 써보는군
	// Exception이란??
	public List<Shop_VO> getShopList(String category) throws Exception;

	public Shop_VO getShopOneList(String idx) throws Exception;
	
	// --------------------------------------------------------------------
	// 회원가입
	public int getMemberRegister(Shop_VO shop_VO) throws Exception;
	
	// Id와 pw로 아이디 및 비밀번호 일치여부 확인 및 로그인
	public Shop_VO getMemberPW_Chk(String m_id) throws Exception;
	
	// 상품 등록
	public int getProductInsert(Shop_VO shop_VO);
	
	// 로그아웃 시 장바구니에 있는 상품 list DB에 저장
	public int getMyStoreSaveInsert(List<Shop_VO> clist);
}
