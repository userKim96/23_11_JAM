package com.koreaIT.java.JAM.vo;

public class Member extends Dto{

	public String name;
	public String loginId;
	public String loginPw;

	public Member(int id, String regDate, String updateDate, String name, String loginId, String loginPw) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.name = name;
		this.loginId = loginId;
		this.loginPw = loginPw;

	}
}