package com.koreaIT.java.JAM.vo;

public class Article extends Dto {

	public int viewCount;
	public String title;
	public String body;

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.viewCount = 0;
		this.title = title;
		this.body = body;

	}
}