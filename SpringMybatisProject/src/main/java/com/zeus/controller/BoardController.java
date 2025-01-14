package com.zeus.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeus.domain.Board;
import com.zeus.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
@MapperScan(basePackages = "com.zeus.mapper")
public class BoardController {
	// 서비스를 이용해서 접근
	@Autowired
	private BoardService service;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(String title, Model model) throws Exception {
		log.info("search");
		Board board = new Board();
		board.setTitle(title);

		model.addAttribute("board", board);
		model.addAttribute("list", service.search(title));
		return "board/list";
	}

	// 게시판 입력폼 요청(/WEB-INF/views/board/register.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Board board, Model model) throws Exception {
		log.info("registerForm");
	}

	// 게시판 입력 내용 저장 후 성공메세지를(/WEB-INF/views/board/success.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Board board, Model model) throws Exception {
		service.register(board);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "board/success";
	}

	// 게시판 전체 리스트를 요청 -> 전체 리스트를 가져와서 (/WEB-INF/views/board/list.jsp)화면에 전달
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		log.info("list");
		model.addAttribute("board", new Board()); 
		model.addAttribute("list", service.list());
	}

	// 게시글 상세내용 요청 -> 한개의 글을 가져와서 (/WEB-INF/views/board/board/read.jsp) 화면에 전달
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	// 게시글 삭제 요청 -> 삭제 진행 -> 결과 (/WEB-INF/views/board/success.jsp) 화면에 전달
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		service.remove(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "board/success";
	}

	// 게시글 수정하기 위한 화면 요청 -> 해당된 게시글 가져와서 (/WEB-INF/views/board/modify.jsp) 화면에 전달
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(int boardNo, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	// 게시글 수정 내용 저장 -> 결과 (/WEB-INF/views/board/success.jsp) 화면에 전달
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Board board, Model model) throws Exception {
		service.modify(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "board/success";
	}

}
