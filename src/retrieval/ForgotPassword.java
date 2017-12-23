package retrieval;

import java.net.URL;

import AddEntriesToDB.authenticate.UserAuth;
import gui.UserScreen;
import gui.conf.ProjectControls;
import gui.conf.ProjectLables;
import gui.conf.message.InfoMessage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ForgotPassword {
	PasswordField passwordField;
	PasswordField repeatPasswordField;
	Stage stage;
	public void getPassword(){
		
	}
	
	public void changePassowrd(){
		Label userPassword = ProjectLables.labelForFields("New Password");
        userPassword.setPrefWidth(200);
        userPassword.setAlignment(Pos.CENTER_RIGHT);

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password.");
        passwordField.setPrefColumnCount(10);
        
        Label repeatPassword = ProjectLables.labelForFields("Re-enter Password");
        repeatPassword.setPrefWidth(200);
        repeatPassword.setAlignment(Pos.CENTER_RIGHT);

        repeatPasswordField = new PasswordField();
        repeatPasswordField.setPromptText("Re-enter Password.");
        repeatPasswordField.setPrefColumnCount(10);
        
        /*** Login Pane ***/
        TilePane loginPane = new TilePane(Orientation.HORIZONTAL);
        loginPane.setPrefColumns(2);
        loginPane.setHgap(15);
        loginPane.setVgap(10);
        loginPane.getChildren().addAll(userPassword, passwordField, repeatPassword, repeatPasswordField);
        loginPane.setAlignment(Pos.CENTER);

        /*** Login button ***/
        Button login = ProjectControls.createButton("Change Password");
        login.setAlignment(Pos.CENTER);
        login.setMaxWidth(150);
        login.setOnAction(e -> {
            /*boolean flag = new UserAuth().authenticate(nameField.getText(),passwordField.getText());
            if(flag){
                UserScreen.createUserScreen(nameField.getText());
            }
            else{
                new InfoMessage().messageBox("Not a valid user");
                nameField.clear();
                nameField.requestFocus();
                passwordField.clear();
            }*/
        });
        
        VBox container = new VBox();
        container.getChildren().addAll(loginPane, login);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10));
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("b3.jpg");
        
        container.setStyle("-fx-background-image: url("+url+")");
        
        Scene scene = new Scene(container, 500, 200);
        
        stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            stage.close();
        	//e.consume();
        });
        stage.setTitle("Change Passowrd");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
	}
}
