package com.ict.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.model.dao.Shop_DAO;
import com.ict.shop.model.vo.Shop_VO;

@Service
public class Cart {
	@Autowired
	private Shop_DAO shop_DAO;

	public void setShop_DAO(Shop_DAO shop_DAO) {
		this.shop_DAO = shop_DAO;
	}

	// static 은 똑같은거 안만들어짐
	private List<Shop_VO> cartlist = new ArrayList<Shop_VO>(); // 카트에 담겨질 제품 리스트
	private int total; // 카트 전체 금액
//	static {
//		// ArrayList를 사용하는구만
//		// ArrayList를 초기화 해서 만들어 놓는다.
//		// 잘 배워두자
//		cartlist = new ArrayList<Shop_VO>();
//	}

	// 카트에 해당 제품이 있는지 없는지 검사하는 메서드
	public Shop_VO findProduct(String idx) {
		Shop_VO shop_VO = null;
		Iterator<Shop_VO> it = cartlist.iterator();
		while (it.hasNext()) {
			Shop_VO shop_VO2 = (Shop_VO) it.next();
//			if (shop_VO2.getIdx().equals("idx")) {
//				
//			}
			if (Integer.parseInt(shop_VO2.getIdx()) == Integer.parseInt(idx)) {
				shop_VO = shop_VO2;
				break;
			}
		}
		return shop_VO;
	}

	// 제품을 카트에 담는 메서드
	public void addProduct(String idx, String m_id) {
		Shop_VO shop_VO = findProduct(idx);
		if (shop_VO != null) {
			// 카트에 있는 현재 개수를 증가
			shop_VO.setQaunt(shop_VO.getQaunt() + 1);
			// sale일때 금액과 sale이 아닐 때 금액
			total = total + shop_VO.getP_saleprice();
		} else {
			Shop_VO shop_VO2 = shop_DAO.getShopOneList(idx);
			shop_VO2.setQaunt(1);
			shop_VO2.setM_id(m_id);
			total = total + shop_VO2.getP_saleprice();
			cartlist.add(shop_VO2);
		}
	}

	public List<Shop_VO> getCartlist() {
		return cartlist;
	}

	public void setCartlist(List<Shop_VO> cartlist) {
		this.cartlist = cartlist;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	// 제품 삭제 할때
	public void deleteProduct(String idx) {
		Shop_VO shop_VO = findProduct(idx);
		if (shop_VO != null) {
			cartlist.remove(shop_VO);
			total = total - shop_VO.getTotalPrice();
		}
	}
	
	// 수량을 변경 할 때
	public void quantEdit(String idx, int su) {
		Shop_VO shop_VO = findProduct(idx);
		if (shop_VO != null) {
			total = total - shop_VO.getTotalPrice();
			shop_VO.setQaunt(su);
			total = total + shop_VO.getTotalPrice();
		}
	}
}














