package com.kh.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@NoArgsConstructor	//디생
//@RequiredArgsConstructor
//@ToString
//@EqualsAndHashCode(of="boardNo")
@Data
@Builder
public class Board {
	private int boardNo;
	@NonNull
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}
