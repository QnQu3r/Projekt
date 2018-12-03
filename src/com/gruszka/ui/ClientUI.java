package com.gruszka.ui;
import javafx.scene.layout.GridPane;

public class ClientUI {
	private GridPane pane;
	
	ClientUI(){
		pane = new GridPane();
		create();
	}
	
	private void create() {
		LogoutButton logoutButton = new LogoutButton("Wyloguj"); //customowy komponent
		pane.add(logoutButton, 0, 0);
	}
	
	public GridPane getPane() {
		return this.pane;
	}
}
