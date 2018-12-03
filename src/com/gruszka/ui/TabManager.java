package com.gruszka.ui;

import com.gruszka.util.TabAction;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabManager {
	private TabPane tabPane = new TabPane();
	private Tab signInTab = new Tab();
	private Tab clientTab = new Tab();
	private Tab officeTab = new Tab();
	private Tab airlineTab = new Tab();
	
	private static volatile TabManager instance;
	
	public static TabManager getInstance() {
		if(instance == null) {
			synchronized(TabManager.class) {
				if(instance == null) instance = new TabManager();
			}
		}
		
		return instance;
	}
	
	private TabManager() {
		if(instance != null) {
			throw new RuntimeException(
					"Use getInstance() to access singleton TabManager");
		}
		setTabsProperties();
		tabPane.getTabs().addAll(signInTab, clientTab, officeTab, airlineTab);
	}
	
	private void setTabsProperties() {
		signInTab.setText("Logowanie");
		signInTab.setClosable(false);
		
		clientTab.setText("Klient");
		clientTab.setClosable(false);
		
		officeTab.setText("Biuro");
		officeTab.setClosable(false);
		
		airlineTab.setText("Firma lotnicza");
		airlineTab.setClosable(false);
		
		signInTab.setContent(new SignInUI().getPane());
		clientTab.setContent(new ClientUI().getPane());
		officeTab.setContent(new OfficeUI().getPane());
		airlineTab.setContent(new AirlineUI().getPane());
		
		//wst�pnie wy��cza dost�p do kart przed zalogowaniem
		clientTab.setDisable(true);
		officeTab.setDisable(true);
		airlineTab.setDisable(true);
	}
	
	public TabPane getTabPane() {
		return this.tabPane;
	}
	
	//tu zale�nie od loginu przerzuca Cie do okienka
	//jesli login zaczyna sie od 'c' przerzuca do kienka klienta,
	//jesli zaczyna sie od 'o' przerzuca do okienka biura
	public void toggle(TabAction action) {
		switch(action) {
		case CLIENT:
			clientTab.setDisable(false);
			airlineTab.setDisable(true);
			officeTab.setDisable(true);
			tabPane.getSelectionModel().select(1); //1 to pozycja karty klienta
			break;
		case LOGOUT:
			tabPane.getSelectionModel().select(0); //0 to pozycja okna logowania
			clientTab.setDisable(true);
			airlineTab.setDisable(true);
			officeTab.setDisable(true);
			break;
		case OFFICE:
			clientTab.setDisable(true);
			airlineTab.setDisable(true);
			officeTab.setDisable(false);
			tabPane.getSelectionModel().select(2); //okno biura
			break;
		case AIRLINE:
			clientTab.setDisable(true);
			officeTab.setDisable(true);
			airlineTab.setDisable(false);
			tabPane.getSelectionModel().select(3); //okno firmy lotniczej
			break;
		}
	}
}
