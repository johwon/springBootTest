package com.kh.domain;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String userId;
	private String password;
	private int coin;
	private Date dateOfBirth;
}
