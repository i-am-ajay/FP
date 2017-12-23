package gui;

import AddEntriesToDB.ChangePassword;
import AddEntriesToDB.authenticate.UserAuth;
import gui.conf.*;
import gui.conf.message.InfoMessage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import retrieval.ForgotPassword;
import retrieval.GeneratePassword;

/**
 * Created by ajay on 4/24/2016.
 */
public class LoginScreenForUser {
    public static Node previousScreen;
    static TextField nameField;
    static PasswordField passwordField;
    static VBox box;
    public static VBox loginScene(){
        /*** Screen Title ***/
        ProjectLables.stringForSubLabel.setValue("Login For User");

        /***  Username ***/
        Label userName = ProjectLables.labelForFields(" Username");
        userName.setPrefWidth(200);
        userName.setAlignment(Pos.CENTER_RIGHT);

        nameField = new TextField();
        nameField.setPromptText("Enter username.");
        nameField.setPrefColumnCount(10);


        /*** User Password ***/
        Label userPassword = ProjectLables.labelForFields("Password");
        userPassword.setPrefWidth(200);
        userPassword.setAlignment(Pos.CENTER_RIGHT);

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password.");
        passwordField.setPrefColumnCount(10);

        /*** Login Pane ***/
        TilePane loginPane = new TilePane(Orientation.HORIZONTAL);
        loginPane.setPrefColumns(2);
        loginPane.setHgap(15);
        loginPane.setVgap(10);
        loginPane.getChildren().addAll(userName, nameField, userPassword, passwordField);
        loginPane.setAlignment(Pos.CENTER);

        /*** Login button ***/
        Button login = ProjectControls.createButton("Login Button");
        login.setAlignment(Pos.CENTER);
        login.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        login.setOnAction(e -> {
            boolean flag = new UserAuth().authenticate(nameField.getText(),passwordField.getText());
            if(flag){
                UserScreen.createUserScreen(nameField.getText());
            }
            else{
                new InfoMessage().messageBox("Not a valid user");
                nameField.clear();
                nameField.requestFocus();
                passwordField.clear();
            }
        });
        /*** Back button ***/
        Button back = ProjectControls.createButton("Back");
        back.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        back.setAlignment(Pos.CENTER);
        back.setOnAction( e->{
            ProjectMainLayout.mainPane().setCenter(previousScreen);
        });
        /*** Button Layout ***/
        TilePane buttonsTilePane = new TilePane(Orientation.HORIZONTAL);
        buttonsTilePane.getChildren().addAll(login,back);
        buttonsTilePane.setHgap(20);
        buttonsTilePane.setAlignment(Pos.CENTER);
        
        // Forgotten Password Link
        Hyperlink link = new Hyperlink();
        link.setText("Forgot Password?");
        link.setOnAction(
        		e ->{
        		String user = nameField.getText();
        		if(user.isEmpty()){
        			new InfoMessage().messageBox("Please Enter Username.");
        			nameField.requestFocus();
        		}
        		else{
	        		ChangePassword cp = new ChangePassword();
	        		cp.changePassword(user);
        		}
        	}
        );
        VBox linkBox = new VBox();
        linkBox.getChildren().addAll(link);
        linkBox.setAlignment(Pos.CENTER_LEFT);
        linkBox.setPadding(new Insets(10));

        /*** Layout of username,password,button ***/
        box = new VBox(30,loginPane,buttonsTilePane,linkBox);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(box, Pos.CENTER);
        return box;
    }
    public static Node getMainScene(){
        return box;
    }
}

