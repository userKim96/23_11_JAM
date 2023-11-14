package com.koreaIT.JAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("명령어 입력)");
			String command = sc.nextLine();
			
			int lastArticleId = 0;
			
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if (command.equals("article list")) {
				System.out.println("게시글이 없습니다.");
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
				
				System.out.println("입력된 제목 : " + title);
				System.out.println("입력된 내용 : " + body);
				System.out.printf("%d번 글이 생성되었습니다.", id);
				
				lastArticleId++;
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
