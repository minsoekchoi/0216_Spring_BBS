package com.ict.bbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.bbs.model.service.BBS_Service;
import com.ict.bbs.model.vo.BBS_VO;

@Controller
public class BBS_Controller {
	@Autowired
	private BBS_Service bbsService;

	private static final Logger logger = LoggerFactory.getLogger(BBS_Controller.class);

	public void setBbsService(BBS_Service bbsService) {
		this.bbsService = bbsService;
	}

	@RequestMapping("bbs_list.do")
	public ModelAndView getList() {
		ModelAndView mv = new ModelAndView("bbs/bbs_list");
		List<BBS_VO> bbs_list = bbsService.getList();
		mv.addObject("bbs_list", bbs_list);
		return mv;
	}

	@RequestMapping("bbs_onelist.do")
	public ModelAndView getOneList(@RequestParam("b_idx") String b_idx) {
		ModelAndView mv = new ModelAndView("bbs/bbs_onelist");
		logger.info("onelist 진입");
		BBS_VO bvo = bbsService.getOneList(b_idx);
		mv.addObject("bvo", bvo);
		return mv;
	}

	@RequestMapping("bbs_write.do")
	public ModelAndView getWrite() {
		ModelAndView mv = new ModelAndView("bbs/bbs_write");
		return mv;
	}

	@RequestMapping("bbs_write_ok.do")
	public ModelAndView getWriteOK(BBS_VO bvo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:bbs_list.do");
		logger.info("게시글 작성 페이지 진입");
		try {
			String path = session.getServletContext().getRealPath("/resources/upload");
			MultipartFile f_param = bvo.getF_param()[0];
			if (f_param.equals("") || f_param == null) {
				bvo.setF_name("");
			} else {
				bvo.setF_name(f_param.getOriginalFilename());
			}
			int result = bbsService.getInsert(bvo);
			if (result > 0) {
				f_param.transferTo(new File(path + "/" + bvo.getF_name()));
			}

		} catch (Exception e) {
		}
		return mv;
	}

	@RequestMapping("bbs_down.do")
	public void getFileDonw(@RequestParam("f_name") String f_name, HttpSession session, HttpServletResponse response) {
		logger.info("파일 다운로드 진입");
		String path = session.getServletContext().getRealPath("/resources/upload/" + f_name);
		try {
			String realpath = URLEncoder.encode(path, "utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + realpath);
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
		}
	}

	@RequestMapping("bbs_update.do")
	public ModelAndView getUpdate(@RequestParam("b_idx") String b_idx) {
		logger.info("게시글 update 진입");
		ModelAndView mv = new ModelAndView("bbs/bbs_update");
		BBS_VO bvo = bbsService.getOneList(b_idx);
		logger.info("진입" + bvo.getPwd());
		mv.addObject("bvo", bvo);
		return mv;
	}

	@RequestMapping("bbs_update_ok.do")
	public ModelAndView getUpdateOK(BBS_VO bvo, HttpServletRequest request) {
		logger.info("게시글 update_ok 진입");
		ModelAndView mv = new ModelAndView();
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		try {
			MultipartFile f_param = bvo.getF_param()[0];
			String old_f_name = request.getParameter("old_f_name");

			File dir = new File(path);
			String[] arr = dir.list();
			List<String> k = Arrays.asList(arr);
			boolean res = k.contains(f_param.getOriginalFilename());
			String ori_filename = f_param.getOriginalFilename();

			if (ori_filename.equals("") || ori_filename == null) {
				bvo.setF_name(old_f_name);
			} else {
				bvo.setF_name(f_param.getOriginalFilename());
				if (!res) {
					f_param.transferTo(new File(path + "/" + bvo.getF_name()));
				}
			}
			int result = bbsService.getUpdate(bvo);
		} catch (Exception e) {
			System.out.println("3" + e);
		}
		System.out.println("못가져오니?" + bvo.getB_idx());
		mv.setViewName("redirect:bbs_onelist.do?b_idx=" + bvo.getB_idx());
		return mv;
	}

	@RequestMapping("bbs_delete.do")
	public ModelAndView getDelete(@RequestParam("b_idx") String b_idx) {
		logger.info("게시글 delete 진입");
		ModelAndView mv = new ModelAndView("bbs/bbs_delete");
		BBS_VO bvo = bbsService.getOneList(b_idx);
		mv.addObject("bvo", bvo);
		return mv;
	}
	
	@RequestMapping("bbs_delete_ok.do")
	public ModelAndView getDeleteOK(@RequestParam("b_idx") String b_idx) {
		logger.info("게시글 delete_ok 진입");
		ModelAndView mv = new ModelAndView("redirect:bbs_list.do");
		bbsService.getDelete(b_idx);
		return mv;
	}

}
