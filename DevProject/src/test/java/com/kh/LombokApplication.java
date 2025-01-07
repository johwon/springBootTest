package com.kh;

import java.util.Date;

import com.kh.domain.Board;
import com.kh.service.BoardService;

public class LombokApplication {
	public static void main(String[] args) {
		
		BoardService bs = new BoardService();
		
		//@Builder Board 만들기
		Board board = Board.builder().boardNo(1).title("빌더테스트").content("내용").regDate(new Date()).build();
		bs.create(board);
		bs.readByBoardNo(1);
		bs.delete(board);
	}
}
