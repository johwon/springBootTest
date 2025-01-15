package com.zeus.domain;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Board {
	private int boardNo;
	//입력값 검색규칙 반드시 입력해야하는 필드선언
	@NotBlank
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}
