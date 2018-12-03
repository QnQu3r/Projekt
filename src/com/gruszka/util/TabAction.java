package com.gruszka.util;

public enum TabAction {
	CLIENT(1),
	OFFICE(2),
	LOGOUT(3),
	AIRLINE(4);
	
	int action;
	private TabAction(int toggle) {
		action = toggle;
	}
}
