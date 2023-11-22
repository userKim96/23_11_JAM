package com.koreaIT.java.JAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.JAM.util.Util;
import com.koreaIT.java.JAM.vo.Member;

public class MemberController extends Controller {
	
	private static List<Member> members;
	private Scanner sc;
	private String actionMethodName;
	private String command;
	private Member loginedMember;
	private static int lastMemberId;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<Member>();
		this.sc = sc;
		this.lastMemberId = 0;
		this.loginedMember = null;
	}
	
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch (actionMethodName) {
		case "join" :
			doJoin();
			break;
		case "login" :
			doLogin();
			break;
		case "logout" :
			doLogOut();
			break;
		case "whoami" :
			showWhoami();
			break;
		default :
			System.out.println("존재하지 않는 세부기능입니다.");
			break;
		}
	}
	
	public void doJoin() {
		
		int id = lastMemberId + 1;
		String regDate = Util.getNow();
		String updateDate = Util.getNow();
		String name = null;
		String loginId = null;
		String loginPw = null;
		
		while (true) {

			System.out.println("이름 : ");
			name = sc.nextLine();

			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요.");
				continue;
			}
			
			if (name.contains(" ") == true) {
				System.out.println("이름에 공백은 입력할 수 없습니다.");
				continue;
			}
			break;
		}

		while (true) {

			System.out.println("아이디 : ");
			loginId = sc.nextLine();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			if (loginId.contains(" ") == true) {
				System.out.println("아이디에 공백은 입력할 수 없습니다.");
				continue;
			}
			if (isMemberLoginIdByloginId(loginId) == true) {
				System.out.println("이미 사용중인 아이디입니다.");
				continue;
			}
			break;
		}

		while (true) {

			System.out.println("비밀번호 : ");
			loginPw = sc.nextLine();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			if (loginPw.contains(" ") == true) {
				System.out.println("비밀번호에 공백은 입력할 수 없습니다.");
				continue;
			}

			System.out.println("비밀번호 체크 : ");
			String loginPwCheck = sc.nextLine();

			if (loginPwCheck.length() == 0) {
				System.out.println("비밀번호 체크를 입력해주세요.");
				continue;
			}
			if (loginPwCheck.contains(" ") == true) {
				System.out.println("비밀번호 체크에 공백은 입력할 수 없습니다.");
				continue;
			}
			if (loginPw.equals(loginPwCheck) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		Member member = new Member(id, regDate, updateDate, name, loginId, loginPw);
		members.add(member);

		System.out.printf("%s님 가입되었습니다.\n", name);

		lastMemberId++;
	}
	
	private void doLogin() {
		
		if (loginedMember != null) {
			System.out.println("로그인 상태입니다.");
			return;
		}
		
		while (true) {
			
			
			System.out.println("아이디 : ");
			String loginId = sc.nextLine();
			
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			if (loginId.contains(" ") == true) {
				System.out.println("아이디에 공백은 입력할 수 없습니다.");
				continue;
			}
			
			Member member = getMemberByloginId(loginId);
			
			if(member == null) {
				System.out.println("존재하지 않는 아이디입니다.");
				continue;
			}
			
			System.out.println("비밀번호 : ");
			String loginPw = sc.nextLine();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			if (loginPw.contains(" ") == true) {
				System.out.println("비밀번호에 공백은 입력할 수 없습니다.");
				continue;
			}
			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			
			loginedMember = member;
			
			System.out.println("로그인 되었습니다.");
			
			break;
		}
	}

	private void doLogOut() {
		if (loginedMember == null) {
			System.out.println("로그아웃 상태입니다.");
			return;
		}
		
		loginedMember = null;
		
		System.out.println("로그아웃 되었습니다.");
	}
	
	private void showWhoami() {
		
		if (loginedMember == null) {
			
			System.out.println("로그아웃 상태에서 이용할 수 없습니다.");
			return;
		}
		
		
		System.out.println("====== 회원 정보 ======\n");
		System.out.printf("번호 : %d\n", loginedMember.id);
		System.out.printf("가입일 : %s\n", loginedMember.regDate);
		System.out.printf("수정일 : %s\n", loginedMember.updateDate);
		System.out.printf("이름 : %s\n", loginedMember.name);
		System.out.printf("아이디 : %s\n", loginedMember.loginId);
		
	}

	private boolean isMemberLoginIdByloginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}

		return false;
	}
	
	private Member getMemberByloginId(String loginId) {
		
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		
		return null;
	}
	
	public static void makeTestMemberDate() {

		for (int i = 1; i <= 5; i++) {

			members.add(new Member(i, Util.getNow(), Util.getNow(), "test" + i, "test" + i, "test" + i));
			lastMemberId++;
		}
		System.out.println("테스트 멤버 데이터 생성완료.");
	}
	

}
