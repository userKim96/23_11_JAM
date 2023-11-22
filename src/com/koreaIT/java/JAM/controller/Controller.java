package com.koreaIT.java.JAM.controller;

import com.koreaIT.java.JAM.vo.Member;

public abstract class  Controller {
	
	protected static Member loginedMember = null;
	
	public boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void doAction(String actionMethodName, String command);
}
