package com.gruszka.ui;

import com.gruszka.util.TabAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import javax.swing.JOptionPane;

public class SignInUI {
	private GridPane pane;
	
	SignInUI(){
		create();
	}
	
	private void create() {
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(40, 40, 40, 40));
		pane.setHgap(36f);
		pane.setVgap(24f);
		
		
		ColumnConstraints c1 = new ColumnConstraints(
				96, 96, 96);
		c1.setHalignment(HPos.RIGHT);
		
		ColumnConstraints c2 = new ColumnConstraints(
				192, 192, 192);
		c2.setHgrow(Priority.ALWAYS);
		
		pane.getColumnConstraints().addAll(c1, c2);
		
		Label headerLabel = new Label("Witamy w biurze Globus!");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		
		Label ctaLabel = new Label("Zaloguj sie");
		ctaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		GridPane.setHalignment(ctaLabel, HPos.CENTER);
		
		Label loginLabel = new Label("Login:");
		Label passwordLabel = new Label("Haslo:");
		TextField loginField = new TextField();
		PasswordField passwordField = new PasswordField();
		
		//wycentrowane headery
		pane.add(headerLabel, 0, 0, 2, 1);
		pane.add(ctaLabel, 0, 1, 2, 1);
		
		//pola i labele do p�l
		pane.add(loginLabel, 0, 2);
		pane.add(loginField, 1, 2);
		pane.add(passwordLabel, 0, 3);
		pane.add(passwordField, 1, 3);
		
		GridPane.setHalignment(loginLabel, HPos.LEFT);
		GridPane.setHalignment(passwordLabel, HPos.LEFT);
		
		Button loginButton = new Button("Zaloguj");
		loginButton.setDefaultButton(true);
		
		pane.add(loginButton, 0, 4, 2, 1);
		GridPane.setHalignment(loginButton, HPos.CENTER);
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
                        
                    public void loginButton (java.awt.event.ActionEvent evt){
                        try{
                            Class.forName("com.mysql.jdbc,Driver");
                            Connection con = DriverManager.getConnection("jbdc:mysql://localhost:3306/globus", "qnquer", "pass123");
                            String sql = "Select* from users where Login=? and Haslo=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, Login.getText());
                            pst.setString(2, Haslo.getText());
                            ResultSet rs = pst.executeQuery();
                            if(rs.next()){
                                
                            }
                            
                                   
                            
                        }
                    catch(Exception e)  {
                    JOptionPane.showMessageDialog(null,e);
                }         
                    }
                    
			/*@Override
			public void handle(ActionEvent event) {
				if(loginField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
							"Blad", "Podaj login!");
					return;
				}
				
				if(passwordField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
							"Blad", "Podaj has�o!");
				}
				
				String uLogin = loginField.getText().toString();
				authenticate(uLogin);
			}*/

                    @Override
                    public void handle(ActionEvent event) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
		});
	}
	
	private void showAlert(Alert.AlertType alertType, Window alertOwner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(alertOwner);
		alert.show();
	}
	
	private void authenticate(String lgn){
		// tu mozna wrzucic switch tyle ze musisz sie bawic z defaultem
		if(lgn.toLowerCase().charAt(0) == 'c') {
			TabManager.getInstance().toggle(TabAction.CLIENT);
		}else if(lgn.toLowerCase().charAt(0) == 'o') {
				TabManager.getInstance().toggle(TabAction.OFFICE);
			}else if(lgn.toLowerCase().charAt(0) == 'a')
				TabManager.getInstance().toggle(TabAction.AIRLINE);
			else{
				showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
						"Blad logowania", "ledny login");
			}
	}
	
	public GridPane getPane() {
		return this.pane;
	}
}
