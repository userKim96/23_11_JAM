package com.koreaIT.java.JAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.JAM.util.Util;
import com.koreaIT.java.JAM.vo.Article;

public class ArticleController extends Controller {
	
	private static List<Article> articles;
	private Scanner sc;
	private String actionMethodName;
	private String command;
	private static int lastArticleId;

	public ArticleController(Scanner sc) {
		this.articles = new ArrayList<Article>();
		this.sc = sc;
		this.lastArticleId = 0;
		
	}
	
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch (actionMethodName) {
		case "list" :
			showList();
			break;
		case "write" :
			doWrite();
			break;
		case "detail" :
			showDetail();
			break;
		case "delete" :
			doDelete();
			break;
		case "modify" :
			doModify();
			break;
		default :
			System.out.println("존재하지 않는 세부기능입니다.");
			break;
		}
	}
	
	public void showList() {
		
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}
		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> forPrintArticles = articles;

		if (searchKeyword.length() > 0) {

			forPrintArticles = new ArrayList<Article>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}

			if (forPrintArticles.size() == 0) {
				System.out.printf("'%s'를(을) 포함한 제목이 없습니다.", searchKeyword);
				return;
			}

			System.out.println("번호   /     제목     /   조회수");

			for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
				Article article = forPrintArticles.get(i);
				System.out.printf("%d   /   %s   /   %d\n", article.id, article.title, article.viewCount);
			}
			return;
		}

		System.out.println("번호   /     제목     /   조회수");

		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			System.out.printf("%d   /   %s   /   %d\n", article.id, article.title, article.viewCount);
		}
		
	}
	
	public void doWrite() {
		int id = lastArticleId + 1;

		String regDate = Util.getNow();

		String updateDate = Util.getNow();

		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("제목을 입력해주세요.");

		System.out.println("내용 : ");
		String body = sc.nextLine();

		if (body.length() == 0) {
			System.out.println("제목을 입력해주세요.");
		}

		Article article = new Article(id, updateDate, regDate, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);

		lastArticleId++;
	}
	
	public void showDetail() {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = null;
		
		foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
		}
		foundArticle.viewCount++;

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성일 : %s\n", foundArticle.regDate);
		System.out.printf("수정일 : %s\n", foundArticle.updateDate);
		System.out.printf("조회수 : %d\n", foundArticle.viewCount);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
	}
	
	public void doDelete() {
		
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle != null) {

			System.out.printf("%d번 게시물을 삭제했습니다.\n", id);

			articles.remove(foundArticle);

			return;
		}
		if (foundArticle == null) {

			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);

			return;
		}
	}
	
	public void doModify() {
		
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		String newUpdateDate = Util.getNow();

		System.out.println("제목 : ");
		String newTitle = sc.nextLine();

		if (newTitle.length() == 0) {
			System.out.println("제목을 입력해주세요.");
		}

		System.out.println("내용 : ");
		String newBody = sc.nextLine();

		if (newBody.length() == 0) {
			System.out.println("제목을 입력해주세요.");
		}

		foundArticle.updateDate = newUpdateDate;
		foundArticle.title = newTitle;
		foundArticle.body = newBody;
		
	}
	
	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	public static void makeTestArticleDate() {

		for (int i = 1; i <= 5; i++) {

			articles.add(new Article(i, Util.getNow(), Util.getNow(), "제목" + i, "내용" + i));
			lastArticleId++;
		}
		System.out.println("테스트 게시글 데이터 생성완료.");
	}


}
