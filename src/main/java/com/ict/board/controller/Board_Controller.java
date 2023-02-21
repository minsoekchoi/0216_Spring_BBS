package com.ict.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.model.service.Board_Service;
import com.ict.board.model.vo.Board_VO;
import com.ict.common.FileReName;
import com.ict.common.Paging;

@Controller
public class Board_Controller {

	@Autowired
	private Board_Service board_Service;

	// common 에 있는 paging 을 쓰기위함
	@Autowired
	private Paging paging;

	// common 에 있는 fileReName 을 쓰기위함
	@Autowired
	private FileReName fileReName;

	private static final Logger logger = LoggerFactory.getLogger(Board_Controller.class);

	public void setBoard_Service(Board_Service board_Service) {
		this.board_Service = board_Service;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public void setFileReName(FileReName fileReName) {
		this.fileReName = fileReName;
	}

	@RequestMapping("board_list.do") // HashMap 개념을 공부하자
	public ModelAndView getBoardList(HttpServletRequest request) {
		logger.info("게시글 진입");
		ModelAndView mv = new ModelAndView("board/board_list");
		int count = board_Service.getTotalCount();
		paging.setTotalRecord(count);

		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 시작 번호와 끝 번호 구하기
		paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerPage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerPage());

		// 시작 블록과 끝 블록 구하기
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 주의 사항(내가 17페이지까지 밖에 없는데 20까지 엔드블록이 측정될때)
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		List<Board_VO> board_list = board_Service.getBoardList(paging.getBegin(), paging.getEnd());
		mv.addObject("board_list", board_list);
		mv.addObject("paging", paging);
		return mv;
	}

	@RequestMapping("board_write.do")
	public ModelAndView getBoardWrite() {
		return new ModelAndView("board/board_write");
	}

	@RequestMapping(value = "board_write_ok.do", method = RequestMethod.POST)
	public ModelAndView getBoardWriteOK(Board_VO board_VO, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:board_list.do");
		String path = session.getServletContext().getRealPath("/resources/upload");
		MultipartFile f_param = board_VO.getF_param();
		try {
			if (f_param.isEmpty()) {
				board_VO.setF_name("");
			} else {
				// 이름중복여부
				String str = fileReName.exec(path, board_VO.getF_param().getOriginalFilename());
				board_VO.setF_name(str);
			}
			mv.addObject("cPage", "1");
			int res = board_Service.getBoardInsert(board_VO);
			if (res > 0) {
				f_param.transferTo(new File(path + "/" + board_VO.getF_name()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}

	@RequestMapping("board_view.do")
	// model 여기서도 사용되고 넘어간다.
	public ModelAndView getBoardView(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("board/board_view");
		// hit 업데이트
		int hit = board_Service.getBoardHit(idx);

		// 상세보기
		Board_VO board_VO = board_Service.getBoardOneList(idx);
		mv.addObject("board_VO", board_VO);
		return mv;
	}

	// 파일 다운로드
	@RequestMapping("board_download.do")
	public void BoardFileDonw(@RequestParam("f_name") String f_name, HttpSession session,
			HttpServletResponse response) {
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

	// 댓글 입력창 들어가기
	@RequestMapping("board_ans_write.do")
	public ModelAndView boardAnsWrite(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx) {
		return new ModelAndView("board/board_ans_write");
	}

	// 댓글 입력
	@RequestMapping("board_ans_write_ok.do")
	public ModelAndView boardAnsWriteOK(@ModelAttribute("cPage") String cPage, @RequestParam("idx") String idx,
			Board_VO board_VO, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:board_list.do");
		try {
			// 상세보기
			Board_VO board_VO2 = board_Service.getBoardOneList(idx);

			int groups = Integer.parseInt(board_VO2.getGroups());
			int step = Integer.parseInt(board_VO2.getStep());
			int lev = Integer.parseInt(board_VO2.getLev());

			step++;
			lev++;

			// DB에 groups, lev를 업데이트해야 된다.
			// groups와 같은 원글을 찾아서 level이 같거나 크면, level을 증가

			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);

			int result = board_Service.getLevUp(map);
			// 레벨업 하고 인서트

			// board_VO 이다. VO2가 아니라
			board_VO.setGroups(String.valueOf(groups));
			board_VO.setStep(String.valueOf(step));
			board_VO.setLev(String.valueOf(lev));

			// 댓글 삽입
			String path = session.getServletContext().getRealPath("/resources/upload");
			MultipartFile f_param = board_VO.getF_param();
			if (f_param.isEmpty()) {
				board_VO.setF_name("");
			} else {
				// 이름중복여부
				String str = fileReName.exec(path, board_VO.getF_param().getOriginalFilename());
				board_VO.setF_name(str);
			}
			int res = board_Service.getBoardAnsInsert(board_VO);
			if (res > 0) {
				f_param.transferTo(new File(path + "/" + board_VO.getF_name()));
			}

		} catch (Exception e) {
		}
		return mv;
	}

	// 글 삭제창 들어가기
	@RequestMapping("board_delete.do")
	public ModelAndView getBoardDelete(@ModelAttribute("idx") String idx, @ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView("board/board_delete");
		// 1.@ModelAttribute 이용하여 vo 없이 시도해보기 로 할려했으나, idx를 이용하여 pwd를 가져와야하는군
		// idx 를 이용 pwd 불러와서 vo 저장하여 pwd 비교하기
		Board_VO board_VO = board_Service.getBoardOneList(idx);
		mv.addObject("board_VO", board_VO);
		
		// 하지만 cPage는 modelAttribute를 이용했으니 담지 말아보자

		return mv;
	}

	// 글 삭제하기
	@RequestMapping("board_delete_ok.do")
	public ModelAndView getBoardDeleteOK(@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx) {
		System.out.println(idx);
		System.out.println("+++++");
		ModelAndView mv = new ModelAndView("redirect:board_list.do");
		Board_VO board_VO = board_Service.getBoardOneList(idx);
		System.out.println(board_VO.getGroups());
		System.out.println(board_VO.getStep());
		System.out.println(board_VO.getLev());
		System.out.println("+++++");
		
		// 그냥 삭제만 하는거면 여기서 끝나면 되는데 groups, step, lev 을 봐야하기에 추가가 되어야 한다.
		// 이건 그냥 삭제하는거
		System.out.println("실험용");
		int atfer_lev = board_Service.getAfter_Lev(board_VO);
		System.out.println("실험용 결과" + atfer_lev);
		// 이게 아니잖아 vo 로 가야겠네
		// board_Service.getBoardDelete(idx);
		
//		4-2 = 2
//		그룹, 
//		
		
		
		
		
		
		return mv;
	}

}
