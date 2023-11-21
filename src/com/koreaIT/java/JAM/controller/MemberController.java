package com.koreaIT.java.JAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.JAM.util.Util;
import com.koreaIT.java.JAM.vo.Member;

public class MemberController {
	
	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
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
			if (isMemberLoginId(loginId) == true) {
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
	
	private boolean isMemberLoginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}

		return false;
	}

}
