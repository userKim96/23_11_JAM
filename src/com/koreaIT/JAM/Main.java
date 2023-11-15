package com.koreaIT.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<Article> articles = new ArrayList<>();
	
	static int lastArticleId = 0;
	
	public static void main(String[] args) {
		
		
		System.out.println("== 프로그램 시작 ==");
		
		makeTestDate();


		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("명령어 입력)");
			String command = sc.nextLine().trim();
			
	
			
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if (command.equals("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				else {
					
					System.out.println("번호   /     제목     /   조회수");
					
					for (int i = articles.size()-1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d   /   %s   /   %d\n", article.id, article.title, article.viewCount);
					}
				}
			}
			else if (command.equals("article write")) {
				
				int id = lastArticleId + 1;
				
				String regDate = Util.getNow();
				
				String updateDate = Util.getNow();
				
				System.out.println("제목 : ");
				String title = sc.nextLine();
				
				if (title.length() == 0) {
					System.out.println("제목을 입력해주세요.");
				}
				
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
			else if (command.startsWith("article detail")) {
				
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.id == id) {
						
						foundArticle = article;
						foundArticle.viewCount++;
						
						System.out.printf("번호 : %d\n", article.id);
						System.out.printf("작성일 : %s\n", article.regDate);
						System.out.printf("수정일 : %s\n", article.updateDate);
						System.out.printf("조회수 : %d\n", article.viewCount);
						System.out.printf("제목 : %s\n", article.title);
						System.out.printf("내용 : %s\n", article.body);
						
						break;
					}
					
					if (foundArticle == null) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					}
				}
				
			}
			else if (command.startsWith("article search")) {
				String[] commandDiv = command.split(" ");
				String searchKeyword = commandDiv[2];
				
				
			}
			
			
			else if (command.startsWith("article delete")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle != null) {
					
					System.out.printf("%d번 게시물을 삭제했습니다.\n", id);
					
					articles.remove(foundArticle);
					
					continue;
				}
				if (foundArticle == null) {
					
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					
					continue;
				}
			}
			else if (command.startsWith("article modify")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
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
			else if (command.equals("exit")) {
				break;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
		}
		
		System.out.println("== 프로그램 종료 ==");
		
		sc.close();
	}

	private static Article getArticleById(int id) {
		
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		
		return null;
	}

	private static void makeTestDate() {
		
		for (int i=1; i <= 5; i++) {
			
			articles.add(new Article(i, Util.getNow(), Util.getNow(), "제목"+i, "내용"+i));
			lastArticleId++;
		}
		System.out.println("테스트 데이터 생성완료.");
	}
	
	
}




class Article {
	int id;
	String regDate;
	String updateDate;
	int viewCount;
	String title;
	String body;
	
	
	Article(int id, String regDate, String updateDate, String title, String body){
		this. id = id;
		this. regDate = regDate;
		this. updateDate = updateDate;
		this. viewCount = 0;
		this. title = title;
		this. body = body;
		
	}
	
}