package com.gruszka.ui;

import java.util.Optional;

import com.gruszka.datamodels.Trip;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OfficeUI {
	
	private GridPane pane;
	
	private ObservableList<Trip> records =
			FXCollections.observableArrayList();
	
	TableView<Trip> tableView = new TableView();
	TableColumn<Trip, String> address = new TableColumn("adres"); 
	//String to placeholder do klasy wycieczka
	//najlepiej zrobic to za pomoca ObservableList i callbacka
	TableColumn<Trip, String>	price = new TableColumn("cena");
	TableColumn<Trip, String> date = new TableColumn("data");
	TableColumn<Trip, String> type = new TableColumn("typ");
	
	OfficeUI(){
		pane = new GridPane();
		create();
	}
	
	@SuppressWarnings("unchecked")
	private void create() {
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(40, 40, 40, 40));
		pane.setHgap(36f);
		pane.setVgap(24f);
		
		address.prefWidthProperty().bind(tableView.widthProperty().multiply(.25));
		date.prefWidthProperty().bind(tableView.widthProperty().multiply(.25));
		price.prefWidthProperty().bind(tableView.widthProperty().multiply(.25));
		type.prefWidthProperty().bind(tableView.widthProperty().multiply(.25));
		
		//TODO
		//do gotowego programu to trzeba zmienic na callback zamiast propertyvaluefactory
		//
		//
		//
		//
		address.setCellValueFactory(new PropertyValueFactory<Trip, String>("Address"));
		date.setCellValueFactory(new PropertyValueFactory<Trip, String>("Date"));
		price.setCellValueFactory(new PropertyValueFactory<Trip, String>("Price"));
		type.setCellValueFactory(new PropertyValueFactory<Trip, String>("Type"));
		
		tableView.setEditable(true);
		tableView.getColumns().addAll(
				type,
				address,
				price,
				date);
		tableView.setItems(records);
		
		GridPane.setVgrow(tableView, Priority.ALWAYS);
		GridPane.setHgrow(tableView, Priority.ALWAYS);
		GridPane.setHalignment(tableView, HPos.CENTER);
		
		LogoutButton logoutButton = new LogoutButton("Wyloguj"); //customowy komponent
		GridPane.setHalignment(logoutButton, HPos.LEFT);
		
		Button addTripButton = new Button("Dodaj wycieczkę");
		Button addTurnusButton = new Button("Dodaj turnus");
		GridPane.setMargin(addTurnusButton, new Insets(0, 0, 0, 200));
		
		Label headerLabel = new Label("Biuro podróży Globus");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		
		//OKIENKO DODAWANIA - 
		Dialog<Trip> dialog = new Dialog<>();
		dialog.setTitle("Dodaj rekord");
		dialog.setHeaderText("Dodawanie rekordu");
		
		ButtonType addButtonType = new ButtonType("Dodaj", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		GridPane dialogGrid = new GridPane();
		dialogGrid.setHgap(10);
		dialogGrid.setVgap(10);
		dialogGrid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField type = new TextField();
		type.setPromptText("Typ");
		
		TextField address = new TextField();
		address.setPromptText("Adres");
		
		TextField price = new TextField();
		price.setPromptText("Cena");
		
		TextField date = new TextField();
		date.setPromptText("Data");
		
		dialogGrid.add(type, 1, 0);
		dialogGrid.add(address, 1, 1);
		dialogGrid.add(price, 1, 2);
		dialogGrid.add(date, 1, 3);
		
		dialogGrid.add(new Label("Typ:"), 0, 0);
		dialogGrid.add(new Label("Adres:"), 0, 1);
		dialogGrid.add(new Label("Cena:"), 0, 2);
		dialogGrid.add(new Label("Data:"), 0, 3);
		
		dialog.getDialogPane().setContent(dialogGrid);
		Platform.runLater(() -> address.requestFocus());
		
		dialog.setResultConverter(button -> {
			if(button == addButtonType) {
				return new Trip(
						type.getText(),
						address.getText(),
						price.getText(),
						date.getText());
			}
			return null;
		});
		
		addTripButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Optional<Trip> result = dialog.showAndWait();
				result.ifPresent(trip ->
						records.add(trip));
				System.out.println(records.size());
			}
		});
		//to samo, na razie nie ma podziału na okienka turnus / wycieczka
		addTurnusButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Optional<Trip> result = dialog.showAndWait();
				result.ifPresent(trip ->
						records.add(trip));
				System.out.println(records.size());
			}
		});
		
		//---
		pane.add(logoutButton, 0, 0);
		pane.add(headerLabel, 0, 0, 2, 1);
		pane.add(tableView, 0, 1);
		pane.add(addTripButton, 0, 2);
		pane.add(addTurnusButton, 0, 2);
	}
	
	public GridPane getPane() {
		return this.pane;
	}
}
