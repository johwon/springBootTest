package com.kh.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.domain.FileMember;
import com.kh.domain.Member;

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

	@PostMapping(value = "/redirect")
	public String redirectMember(Member member, RedirectAttributes rttr) {
		log.info("redirectMember");
		rttr.addFlashAttribute("member", member);
		return "redirect:/member/result";
	}

	@GetMapping(value = "/result")
	public String resultMember() {
		log.info("resultMember");
		return "result";
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

	@RequestMapping(value = "/registerSpringFormCheckboxes01", method = RequestMethod.GET)
	public String registerSpringFormCheckboxes01(Model model) {
		log.info("registerSpringFormCheckboxes01");

		Map<String, String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("01", "Sports");
		hobbyMap.put("02", "Music");
		hobbyMap.put("03", "Movie");

		model.addAttribute("hobbyMap", hobbyMap);
		model.addAttribute("member", new Member());

		return "registerSpringFormCheckboxes01"; // 뷰 파일명
	}

	@RequestMapping(value = "/registerSpringFormErrors", method = RequestMethod.GET)
	public String registerSpringFormErrors(Model model) {
		log.info("registerSpringFormErrors");

		Member member = new Member();

		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길동");

		model.addAttribute("member", member);

		return "registerSpringFormErrors"; // 뷰 파일명
	}

	// 입력 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated Member member, BindingResult result) {
		log.info("register");

		// 에러 처리
		if (result.hasErrors()) {
			return "registerSpringFormErrors";
		}

		log.info("member.getUserId() = " + member.getUserId());
		log.info("member.getUserName() = " + member.getUserName());
		log.info("member.getEmail() = " + member.getEmail());

		return "errorsResult";
	}
}
