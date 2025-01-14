package com.zeus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.domain.Item;
import com.zeus.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
@MapperScan(basePackages = "com.zeus.mapper")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Value("${upload.path}")
	private String uploadPath;

	// 파일 리스트 요청 (/WEB-INF/views/item/list.jsp)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		List<Item> itemList = this.itemService.list();
		model.addAttribute("itemList", itemList);
	}

	// 파일 등록폼 요청 (/WEB-INF/views/item/register.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Model model) {
		model.addAttribute(new Item());

		return "item/register";
	}

	// 파일 등록내용 db저장 및 파일 저장 (/WEB-INF/views/item/success.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();

		log.info("originalName: " + file.getOriginalFilename());
		log.info("size: " + file.getSize());
		log.info("contentType: " + file.getContentType());
		// uploadFile(String originalName, byte[] fileData) 구조임
		// file.getBytes() => byte[] => 이미지 파일을 byte[]로 전달해줌
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		itemService.regist(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");

		return "item/success";
	}

	// 파일 수정폼 요청 (/WEB-INF/views/item/modify.jsp)
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(Integer itemId, Model model) throws Exception {
		Item item = this.itemService.read(itemId);

		model.addAttribute(item);

		return "item/modify";
	}

	// 파일 수정 내용 db 저장 및 파일 저장 (/WEB-INF/views/item/success.jsp)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();
		
		if (file != null && file.getSize() > 0) {
			//이전값없애기 여기서
			String deletePath = "c:/upload//"+item.getPictureUrl();
			File deleteFile = new File(deletePath);
			deleteFile.delete();
			
			log.info("originalName: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
			log.info("contentType: " + file.getContentType());

			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());

			item.setPictureUrl(createdFileName);
			
			
		}
		this.itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item/success";
	}

	// 파일 삭제폼 요청 (/WEB-INF/views/item/remove.jsp)
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeForm(Integer itemId, Model model) throws Exception {
		Item item = this.itemService.read(itemId);
		model.addAttribute(item);
		return "item/remove";
	}

	// 파일 삭제 (/WEB-INF/views/item/success.jsp)
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(Item item, Model model) throws Exception {
		this.itemService.remove(item.getItemId());

		model.addAttribute("msg", "삭제가 완료되었습니다.");

		return "item/success";
	}

	// 멤버함수
	// 파일명을 주면 (중복이일어나지않는이름_파일.확장자) 만들고 c:upload에 저장
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		// 절대 중복이 일어나지 않는 아이디 생성
		UUID uid = UUID.randomUUID();
		// e6f15559-5da9-40b5-95b9-f2a5dea8c134_carousel1.jpg 아이디 생성
		String createdFileName = uid.toString() + "_" + originalName;
		// c:/upload/e6f15559-5da9-40b5-95b9-f2a5dea8c134_carousel1.jpg 파일로 만들기
		File target = new File(uploadPath, createdFileName);
		// 진짜 데이터를 c:/upload/e6f15559-5da9-40b5-95b9-f2a5dea8c134_carousel1.jpg에 복사 byte[]로
		FileCopyUtils.copy(fileData, target);
		return createdFileName;
	}

	// 브라우저에서 파일 경로를 찾아서 <img src="/item/display/2"/> db에서 불러서 화면에 전달
	@ResponseBody
	@RequestMapping("/display")
	public ResponseEntity<byte[]> displayFile(Integer itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		//e6f15559-5da9-40b5-95b9-f2a5dea8c134_carousel1.jpg
		String fileName = itemService.getPicture(itemId);
		log.info("FILE NAME: " + fileName);
		try {
			//e6f15559-5da9-40b5-95b9-f2a5dea8c134_carousel1.jpg => "jpg"
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			//"jpg"=>MediaType.IMAGE_JPEG
			MediaType mType = getMediaType(formatName);
			//헤더 생성
			HttpHeaders headers = new HttpHeaders();
			//c:/upload//e6f15559-5da9-40b5-95b9-f2a5dea8c134_carousel1.jpg
			in = new FileInputStream(uploadPath + File.separator + fileName);

			if (mType != null) {
				//MediaType.IMAGE_JPEG 헤더에 추가
				headers.setContentType(mType);
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}

	private MediaType getMediaType(String formatName) {
		if (formatName != null) {
			if (formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}

			if (formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}

			if (formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}
		return null;
	}
}