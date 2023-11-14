package com.koreaIT.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		List<Article> articles = new ArrayList<>();
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("명령어 입력)");
			String command = sc.nextLine().trim();
			
			int lastArticleId = 0;
			
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
					
					System.out.println("번호   /   제목");
					
					for (int i = articles.size()-1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d   /   %s   /   %s", article.id, article.title);
					}
				}
			}
			else if (command.equals("article write")) {
				
				int id = lastArticleId + 1;
				
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
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				
				lastArticleId++;
			}
			else if (command.startsWith("article detail")) {
				
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);
				boolean found = false;
				
				for (Article article : articles) {
					if (article.id == id) {
						found = true;
						System.out.printf("번호 : %d", article.id);
						System.out.printf("제목 : %s", article.title);
						System.out.printf("내용 : %s\n", article.body);
						break;
					}
					
					if (found == false) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
					}
				}
				
			}
			else if (command.startsWith("article delete")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);
				boolean found = false;
				
				for (Article article : articles) {
					if (article.id == id) {
						found = true;
						System.out.printf("%d번 게시물을 삭제했습니다.", id);
						articles.remove(article);
						
						break;
					}
					if (found == false) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
					}
				}
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
}


class Article {
	int id;
	String title;
	String body;
	
	Article(int id, String title, String body){
		this. id = id;
		this. title = title;
		this. body = body;
	}
	
}