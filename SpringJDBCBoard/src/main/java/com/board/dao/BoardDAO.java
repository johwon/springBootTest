package com.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.board.domain.Board;

@Repository
public class BoardDAO {
	
	//jdbc template 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static String insert = "insert into jdbcboard(board_no,title,content,writer) "
			+ "values(jdbcBoard_seq.NEXTVAL, ?, ?, ?)";
	public static String list = "SELECT board_no, title, content, writer, reg_date FROM "
			+ "jdbcBoard WHERE board_no > 0 ORDER BY board_no desc, reg_date DESC";
	
	//curd 함수를 처리한다
	//insert
	public void create(Board board) throws Exception{
		jdbcTemplate.update(insert, board.getTitle(), board.getContent(), board.getWriter());
	}
	
	public List<Board> list() throws Exception{
		
		
		
		return null;
	}
}
