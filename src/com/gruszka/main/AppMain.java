package com.gruszka.main;

import com.gruszka.ui.TabManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
public class AppMain extends Application{
	
	private TabManager tabManager;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("init");
		tabManager = TabManager.getInstance();
		
		Scene scene = new Scene(tabManager.getTabPane(), 800, 800);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		
		primaryStage.setMaxHeight(primaryScreenBounds.getHeight());
		primaryStage.setMaxWidth(primaryScreenBounds.getWidth());
		
		primaryStage.setMinHeight(primaryScreenBounds.getHeight());
		primaryStage.setMinWidth(primaryScreenBounds.getWidth());
		
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Globus");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(event -> {
			System.out.println("app closed by user");
		});
	}
         
	
}
