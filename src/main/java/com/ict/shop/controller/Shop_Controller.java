package com.ict.shop.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.Cart;
import com.ict.common.FileReName;
import com.ict.shop.model.service.Shop_Service;
import com.ict.shop.model.vo.Shop_VO;

// Spring은 Annotation을 적극적으로 활용하는 FrameWork이다.
// FrameWork란?? library와의 차이는??
@Controller
public class Shop_Controller {
	@Autowired
	private Shop_Service shop_Service;

	@Autowired
	private Cart cart;
	
	@Autowired
	private FileReName fileReName;

	// 우리가 만드는건 사용자 페이지
	// 관리자 페이지에서 처리
	// 따라서 파일을 올리고 하는 처리를 할 필요가 없다.
	// 이미지만 가져오면 되니까
	// @Autowired
	// private FileReName fileReName;

	public void setShop_Service(Shop_Service shop_Service) {
		this.shop_Service = shop_Service;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public void setFileReName(FileReName fileReName) {
		this.fileReName = fileReName;
	}

	// 회원용
	@RequestMapping("shop_list.do")
	public ModelAndView getShopList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/product_list");
		String category = request.getParameter("category");
		if (category == null || category == "") {
			category = "ele002";
		}
		try {
			List<Shop_VO> shopList = shop_Service.getShopList(category);
			mv.addObject("shopList", shopList);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}
	
	// 관리자용
	@RequestMapping("shop_admin_list.do")
	public ModelAndView getShopAdminList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/admin_product");
		return mv;
	}

	@RequestMapping("shop_onelist.do")
	public ModelAndView getShopOneList(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("shop/product_content");
		try {
			Shop_VO shop_VO = shop_Service.getShopOneList(idx);
			mv.addObject("shop_VO", shop_VO);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}

	// 1. session 을 이용한 방법
	@RequestMapping("shop_addcart.do")
	public ModelAndView getAddCart(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("redirect:shop_onelist.do");
		try {
			// 카트에 추가
			cart.addProduct(idx);
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}

	@RequestMapping("shop_showcart.do")
	public ModelAndView getShowCart() {
		ModelAndView mv = new ModelAndView("shop/cartList");
		List<Shop_VO> clist = cart.getCartlist();
		int total = cart.getTotal();
		mv.addObject("clist", clist);
		mv.addObject("total", total);
		return mv;
	}

	@RequestMapping("shop_editcart.do")
	public ModelAndView getEditCart(@RequestParam("idx") String idx, @RequestParam("su") String su) {
		ModelAndView mv = new ModelAndView("redirect:shop_showcart.do");
		cart.quantEdit(idx, Integer.parseInt(su));
		return mv;
	}

	@RequestMapping("shop_deletecart.do")
	public ModelAndView getDeleteCart(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("redirect:shop_showcart.do");
		cart.deleteProduct(idx);
		return mv;
	}

	// ---------------------------------------------------------------------------------------
	// 로그인
//	@RequestMapping("login.do")
//	public ModelAndView getLogin() {
//		ModelAndView mv = new ModelAndView("shop/login");
//		return mv;
//	}

	// 로그인 & 이제 DB에서 id 및 pw 확인하여 로그인까지
	// 1. login 시도
	@RequestMapping("login.do")
	public ModelAndView getLogin() {
		ModelAndView mv = new ModelAndView("shop/login");
		return mv;
	}

	// 로그인 시 아이디 및 비밀번호를 확인해야해서
	// shop_list.do전에
	// id 와 pw를 확인하는 곳을 두군데 더 들려야하는데
	// 기존 login 창을 똑같이 쓰고 alert만 뜨게 하면 되겠네
	// 2. id에 따른 pw DB에서 가져오기
	@RequestMapping("login_ok.do")
	public ModelAndView getLoginOK(@RequestParam("m_id") String m_id, @RequestParam("m_pw") String m_pw) {
		ModelAndView mv = new ModelAndView("redirect:shop_list.do");
		String admin_name = "admin";
		if (m_id.equals(admin_name)) {
			try {
				Shop_VO shop_VO = shop_Service.getMemberPW_Chk(m_id);
				if (shop_VO.getM_pw().equals(m_pw)) {
					mv.addObject("shop_VO", shop_VO);
					return new ModelAndView("redirect:shop_admin_list.do");
				} else {
					return new ModelAndView("shop/login_chk");
				}
			} catch (Exception e) {
				System.out.println(e);
				return new ModelAndView("shop/error");
			}
		} else {
			try {
				Shop_VO shop_VO = shop_Service.getMemberPW_Chk(m_id);
				if (shop_VO.getM_pw().equals(m_pw)) {
					mv.addObject("shop_VO", shop_VO);
					return mv;
				} else {
					return new ModelAndView("shop/login_chk");
				}
			} catch (Exception e) {
				System.out.println(e);
				return new ModelAndView("shop/error");
			}
		}

	}

	// 회원가입버튼 눌렀을때
	@RequestMapping("member_registration.do")
	public ModelAndView getMemberRegistration() {
		ModelAndView mv = new ModelAndView("shop/member_registration");
		return mv;
	}

	// 회원가입하기
	@RequestMapping(value = "member_registration_ok.do", method = RequestMethod.POST)
	public ModelAndView getMemberRegistrationOK(Shop_VO shop_VO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/member_registration_success");
		try {
			int result = shop_Service.getMemberRegister(shop_VO);
			if (result > 0) {
				System.out.println("회원가입 성공");
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("shop/error");
		}
		return mv;
	}

	// 회원가입성공 축하창
	@RequestMapping("member_registration_success.do")
	public ModelAndView getMemberRegistrationOK_Success() {
		ModelAndView mv = new ModelAndView("shop/login");
		return mv;
	}
	
	// 상품 등록
	@RequestMapping("product_reg.do")
	public ModelAndView getProductRegistration(Shop_VO shop_VO, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:shop_list.do");
		try {
			
		String path = session.getServletContext().getRealPath("/resources/upload");
		MultipartFile file_name_l = shop_VO.getF_param_l()[0];
		if (file_name_l.isEmpty()) {
			shop_VO.setP_image_l("");
		} else {
			String str_l = fileReName.exec(path, shop_VO.getF_param_l()[0].getOriginalFilename());
			shop_VO.setP_image_l(str_l);
		}
		
		MultipartFile file_name_s = shop_VO.getF_param_s()[0];
		if (file_name_s.isEmpty()) {
			shop_VO.setP_image_s("");
		} else {
			String str_s = fileReName.exec(path, shop_VO.getF_param_s()[0].getOriginalFilename());
			shop_VO.setP_image_s(str_s);
		}
		int result = shop_Service.getProductInsert(shop_VO);
		if (result > 0) {
			file_name_l.transferTo(new File(path+"/"+shop_VO.getP_image_l()));
			file_name_s.transferTo(new File(path+"/"+shop_VO.getP_image_s()));
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return mv;
	}

}
