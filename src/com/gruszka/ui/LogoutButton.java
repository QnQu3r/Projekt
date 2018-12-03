package com.gruszka.ui;

import com.gruszka.util.TabAction;
import javafx.scene.control.Button;

public class LogoutButton extends Button{

	    public LogoutButton (String text) {
	        setText(text);
	        setOnMouseClicked(e -> {
	        	TabManager.getInstance().toggle(TabAction.LOGOUT);
	        });
	    }
}
