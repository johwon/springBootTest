package com.board.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity	//이 클래스를 db 테이블과 바로 매칭시키겠다(db에서 테이블 미리 안 만들어도 됨)
@SequenceGenerator(
	name="JPABOARD_SEQ_GEN",
	sequenceName="JPABOARD_SEQ",
	initialValue = 1,
	allocationSize = 1
		)
@Table(name="jpaboard")
public class Board {
	@Id		//pk 지정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPABOARD_SEQ_GEN")
	@Column(name="board_no")
	private long boardNo;
	
	@Column(name="title")	//안써도됨
	private String title;
	
	@Column(name = "content") 
	private String content; 
	
	@Column(name = "writer") 
	private String writer; 
	
	@CreationTimestamp
	@Column(name = "reg_date") 
	private Date regDate; 
	
}
