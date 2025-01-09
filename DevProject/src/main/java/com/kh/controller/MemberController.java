package com.kh.controller;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.domain.Address;
import com.kh.domain.Board;
import com.kh.domain.FileMember;
import com.kh.domain.Member;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@PostMapping(value = "/insert")
	public String insertMember(Member member) {
		log.info("insertMember");
		return "home";
	}

	@RequestMapping(value = "/registerFileUp01", method = RequestMethod.POST)
	public String registerFileUp01(FileMember filemember) throws Exception {
		log.info("registerFileUp01");

		if (!filemember.getPicture().isEmpty()) {
			for (MultipartFile data : filemember.getPicture()) {
				log.info("originalName: " + data.getOriginalFilename());
				log.info("size: " + data.getSize());
				log.info("contentType: " + data.getContentType());
				if (!data.isEmpty()) {
					String fileName = data.getOriginalFilename(); // db에는 fileName을 넣음
					data.transferTo(new File("C://SpringBootProject//upload_files/" + fileName));
				}
			}
		}

		return "home";
	}

	@PostMapping(value = "/register06")
	public ResponseEntity<String> register06(@RequestBody List<Member> memberList) {
		log.info("register06");
		for (Member member : memberList) {
			log.info("userId = " + member.getUserId());
			log.info("password = " + member.getPassword());
		}
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}

	@PostMapping(value = "/uploadAjax", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		String originalFilename = file.getOriginalFilename();
		log.info("originalName: " + originalFilename);
		log.info("size: " + file.getSize());
		log.info("contentType: " + file.getContentType());
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename(); // db에는 fileName을 넣음
			file.transferTo(new File("C://SpringBootProject//upload_files/" + fileName));
		}
		ResponseEntity<String> entity = new ResponseEntity<String>("UPLOAD SUCCESS " + originalFilename, HttpStatus.OK);
		return entity;
	}

}
