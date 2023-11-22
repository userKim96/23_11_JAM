package com.koreaIT.java.JAM;

import java.util.Scanner;

import com.koreaIT.java.JAM.controller.ArticleController;
import com.koreaIT.java.JAM.controller.Controller;
import com.koreaIT.java.JAM.controller.MemberController;

public class App {

	App() {

	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		Controller controller;
		
		ArticleController.makeTestArticleDate();
		MemberController.makeTestMemberDate();

		while (true) {

			System.out.println("명령어 입력)");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if (command.equals("exit")) {
				break;
			}
			
			String[] commandDiv = command.split(" ");
			String controllerName = commandDiv[0];
		
			if (commandDiv.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
		
			String actionMethodName =commandDiv[1];
			
			
			controller = null;
			
			if (controllerName.equals("article")) {
				controller = articleController;
			}
			else if (controllerName.equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 기능입니다.");
				continue;
			}
			
			controller.doAction(actionMethodName, command);
			
		}
		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}
