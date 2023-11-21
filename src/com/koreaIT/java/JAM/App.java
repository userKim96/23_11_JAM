package com.koreaIT.java.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.JAM.controller.ArticleController;
import com.koreaIT.java.JAM.controller.MemberController;
import com.koreaIT.java.JAM.vo.Article;
import com.koreaIT.java.JAM.vo.Member;

public class App {

	List<Article> articles;
	List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		ArticleController.makeTestDate();

		while (true) {

			System.out.println("명령어 입력)");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article detail")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article delete")) {
				articleController.doDelete(command);
			} else if (command.startsWith("article modify")) {
				articleController.doModify(command);
			} else if (command.equals("exit")) {
				break;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
		}
		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}
