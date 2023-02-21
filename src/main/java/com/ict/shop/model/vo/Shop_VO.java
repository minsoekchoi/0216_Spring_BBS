package com.ict.shop.model.vo;

import org.springframework.web.multipart.MultipartFile;

public class Shop_VO {
	private String idx, category, p_num, p_name, p_company, p_image_s, p_image_l, p_content, p_date;
	private int p_price, p_saleprice;
	private MultipartFile[] f_param_s, f_param_l;
	
	// MultipartFile이란??????????
	// 실제로는 사용자 페이지에서는 필요없지만
	// 관리자 페이지에서 필요하다
	// MultipartFile shop_image_s, shop_image_1;

	public MultipartFile[] getF_param_s() {
		return f_param_s;
	}

	public void setF_param_s(MultipartFile[] f_param_s) {
		this.f_param_s = f_param_s;
	}

	public MultipartFile[] getF_param_l() {
		return f_param_l;
	}

	public void setF_param_l(MultipartFile[] f_param_l) {
		this.f_param_l = f_param_l;
	}

	// 별도 추가(장바구니 때문에)
	private int qaunt, totalPrice;

	// 로그인 & 회원가입을 위한 값
	private String m_idx, m_id, m_pw, m_name, m_reg, m_addr;

	public String getM_idx() {
		return m_idx;
	}

	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_reg() {
		return m_reg;
	}

	public void setM_reg(String m_reg) {
		this.m_reg = m_reg;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	// Sale Price으로 Sale Percent 계산 (할인률)_ex) 20%
	public int getPercent() {
		float per = (p_price - p_saleprice) * 100 / p_price;
		return (int) per;
	}

	// 장바구니에서 수량(quantity)이 변경되면 총 금액도 변경 되어야 한다.
	public void setQaunt(int qaunt) {
		this.qaunt = qaunt;
		// 나중에는 Sale item과 아닌 item으로 나눠서 계산
		setTotalPrice(qaunt * p_saleprice);
	}

	public int getQaunt() {
		return qaunt;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_company() {
		return p_company;
	}

	public void setP_company(String p_company) {
		this.p_company = p_company;
	}

	public String getP_image_s() {
		return p_image_s;
	}

	public void setP_image_s(String p_image_s) {
		this.p_image_s = p_image_s;
	}

	public String getP_image_l() {
		return p_image_l;
	}

	public void setP_image_l(String p_image_l) {
		this.p_image_l = p_image_l;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public int getP_saleprice() {
		return p_saleprice;
	}

	public void setP_saleprice(int p_saleprice) {
		this.p_saleprice = p_saleprice;
	}

}
