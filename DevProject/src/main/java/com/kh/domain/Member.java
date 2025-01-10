package com.kh.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {
	@NotBlank(message="공백이나 빈칸일 수 없습니다.")
	private String userId;
	@NotBlank
	private String password;
	@Email
	private String email;
	@NotBlank(message="공백이나 빈칸일 수 없습니다.")
	@Size(max=3)
	private String userName;
	private int coin;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dateOfBirth; 
	private List<String> hobbyList;
	private String gender; 
}
