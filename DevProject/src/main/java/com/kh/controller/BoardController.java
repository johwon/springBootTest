package com.kh.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.domain.Board;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	// params방식
	@GetMapping(value = "/get", params = "register")
	public String registerForm() {
		log.info("params매핑 register GET방식");
		return "board/register";
	}
	
	@PostMapping(value = "/get", params = "register")
	public String register() {
		log.info("params매핑 register POST방식");
		return "board/register";
	}
	
	@GetMapping(value="/get", params="modify")
	public String modifyForm() {
		log.info("params매핑 modify GET방식");
		return "board/modify";
	}
	
	@PostMapping(value="/get", params="modify")
	public String modify() {
		log.info("params매핑 modify POST방식");
		return "board/modify";
	}
	
	@GetMapping(value = "/get", params="remove") 
	public String removeForm() { 
	log.info("params매핑 remove GET방식"); 
	return "board/remove"; 
	} 
	 
	@PostMapping(value = "/post", params="remove") 
	public String remove() { 
	log.info("params매핑 remove POST방식"); 
	return "board/list"; 
	} 
	
	@GetMapping(value = "/get", params = "list")
	public String list() {
		log.info("params매핑 list POST방식");
		return "board/list";
	}
	
	@GetMapping(value = "/get", params = "read")
	public String read() {
		log.info("params매핑 read GET방식");
		return "board/read";
	}

	// path방식
	/*
	 * @GetMapping(value="/register") public String registerForm() {
	 * log.info("register GET방식"); return "register"; }
	 * 
	 * @PostMapping(value="/register") public String register() {
	 * log.info("register POST방식"); return "register"; }
	 * 
	 * @GetMapping(value="/modify") public String modifyForm() {
	 * log.info("modify GET방식"); return "modify"; }
	 * 
	 * @PostMapping(value="/modify") public String modify() {
	 * log.info("modify POST방식"); return "modify"; }
	 * 
	 * @PostMapping(value="/remove") public String remove() {
	 * log.info("remove POST방식"); return "remove"; }
	 * 
	 * @GetMapping(value="/list") public String list() { log.info("list GET방식");
	 * return "list"; }
	 */

	@PostMapping(value = "/{boardno}")
	public ResponseEntity<String> modify(@PathVariable("boardno") int boardno, @RequestBody Board board) {
		log.info("boardno = " + boardno);
		log.info("board = " + board);

		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}

	@PutMapping(value = "/{boardno}")
	public ResponseEntity<String> modifyByHeader(@PathVariable("boardno") int boardno, @RequestBody Board board) {
		log.info("header boardno = " + boardno);
		log.info("header board = " + board);

		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	@GetMapping(value = "/{boardno}", produces = "application/json")
	public ResponseEntity<Board> boardGetOne(@PathVariable("boardno") int boardno) {
		log.info("get boardno = " + boardno);

		Board board = new Board(); 
		board.setTitle("제목"); 
		board.setContent("내용입니다."); 
		board.setWriter("홍길동"); 
		board.setRegDate(new Date()); 
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK); 
		
		return entity;
	}

}
