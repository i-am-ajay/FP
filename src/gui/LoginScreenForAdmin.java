package gui;

import AddEntriesToDB.authenticate.AdminAuth;
import AddEntriesToDB.authenticate.Authentication;
import gui.conf.*;
import gui.conf.message.InfoMessage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 *  Class to create Admin Login Screen.
 */
public class LoginScreenForAdmin {
    static Pane previousScreen;
    static String previousTitle;
    static VBox box;
    static String subLevelTitle = "Login For Admin";
    static TextField nameField;
    static PasswordField passwordField;
    public static VBox loginScene(){
        /*** Screen Title ***/
        ProjectLables.stringForSubLabel.setValue(subLevelTitle);

        /*** Admin username ***/
        Label adminName = ProjectLables.labelForFields("Admin Username");
        adminName.setPrefWidth(200);
        //adminName.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        adminName.setAlignment(Pos.CENTER_RIGHT);

        nameField = new TextField();
        //nameField.setPrefColumnCount(20);
        nameField.setPromptText("Enter admin username.");

        /*HBox nameBox = new HBox(20,adminName,nameField);
        nameBox.setAlignment(Pos.CENTER);*/

        /*** Admin Password ***/
        Label adminPassword = ProjectLables.labelForFields("Password");
        adminPassword.setPrefWidth(200);
        adminPassword.setAlignment(Pos.CENTER_RIGHT);
        adminPassword.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        passwordField = new PasswordField();
        //passwordField.setPrefColumnCount(20);
        passwordField.setPromptText("Enter Password.");

        TilePane loginPane = new TilePane(Orientation.HORIZONTAL);
        //loginPane.setPrefColumns(2);
        //loginPane.setPrefRows(2);
        loginPane.getChildren().addAll(adminName, nameField, adminPassword, passwordField);
        loginPane.setHgap(10);
        loginPane.setVgap(20);

        /*HBox passwordBox = new HBox(20,adminPassword,passwordField);
        passwordBox.setAlignment(Pos.CENTER);*/

        /*** Login button ***/
        Button login = ProjectControls.createButton("Login Button");
        //login.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //login.setAlignment(Pos.CENTER_LEFT);
        login.setOnAction(e -> {
                AdminScreen.setPreviousTitle(subLevelTitle);
                AdminScreen.setPreviousScreen(box);
                Authentication auth = new AdminAuth();
                boolean flag = auth.authenticate(nameField.getText(),passwordField.getText());
                if(flag == true) {
                    AdminScreen.compileAdminScreen();
                }
                else{
                    new InfoMessage().messageBox("Not a valid login.");
                    nameField.clear();
                    nameField.requestFocus();
                    passwordField.clear();
                }

            }
        );
        /*** Back button ***/
        Button back = ProjectControls.createButton("Back");
        back.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //back.setAlignment(Pos.CENTER_RIGHT);
        back.setOnAction( e->{
            ProjectMainLayout.mainPane().setCenter(getPreviousScreen());
            ProjectLables.stringForSubLabel.setValue(getPreviousTitle());
        });
        /*** Button Layout ***/
        TilePane buttonsTilePane = new TilePane(Orientation.HORIZONTAL);
        buttonsTilePane.getChildren().addAll(login, back);
        buttonsTilePane.setHgap(20);
        buttonsTilePane.setAlignment(Pos.CENTER);


        /*** Layout of username,password,button ***/
        //VBox box = new VBox(30,nameBox,passwordBox,buttonsTilePane);
        box = new VBox(30,loginPane,buttonsTilePane);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);
        //System.out.println(ProjectLables.labelWidthProperty.getValue());
        BorderPane.setAlignment(box, Pos.CENTER);
        return box;
    }

    public static void setPreviousScreen(Pane screen){
        previousScreen = screen;
    }
    public static Pane getPreviousScreen(){
        return previousScreen;
    }

    public static void setPreviousTitle(String title){
        previousTitle = title;
    }
    public static String getPreviousTitle(){
        return previousTitle;
    }
    public static void clear(){

    }
}
