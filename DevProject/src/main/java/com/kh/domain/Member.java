package com.kh.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Member {
	@NotBlank(message="공백이나 빈칸일 수 없습니다.")
	private String userId;
	private String password;
	private String email;
	private String userName;
	private int coin;
	private Date dateOfBirth;
	private List<String> hobbyList;
}
