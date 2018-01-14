package gui;

import AddEntriesToDB.CreateAdmin;
import globals.StoreNewUserInfo;
import gui.conf.*;
import gui.conf.message.AdminConfirmationBox;
import gui.conf.message.ConfirmationBox;
import gui.conf.message.InfoMessage;
import gui.conf.message.UserCreationConfirmationBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 6/3/2016.
 */
public class CreateUser {
    public static VBox mainScreen;
    static StoreNewUserInfo userInfo;
    static TextField usernameField;
    static PasswordField passwordField;
    static PasswordField rePasswordField;
    static TextField masterField;
    static ToggleButton createUserToggleButton;
    static ToggleButton createAdminToggleButton;
    private static TextField emailField;


    public static void createUserOrAdmin() {
        userInfo = new StoreNewUserInfo();
        ProjectLables.stringForSubLabel.setValue("Create User/Admin");
        Label userName = ProjectLables.labelForFields("UserName");
        usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        userName.setLabelFor(usernameField);

        Label password = ProjectLables.labelForFields("Password");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        password.setLabelFor(passwordField);
        
        Label repeatPassword = ProjectLables.labelForFields("Repeat Pass");
        rePasswordField = new PasswordField();
        rePasswordField.setPromptText("Re-Enter password");
        repeatPassword.setLabelFor(rePasswordField);

        Label email = ProjectLables.labelForFields("Email");
        emailField = new TextField();
        emailField.setPromptText("Enter valid email");
        password.setLabelFor(emailField);

        Label masterKey = ProjectLables.labelForFields("Master Key");
        masterField = new TextField();
        masterKey.setLabelFor(masterField);

        TilePane fieldsPane = new TilePane();
        fieldsPane.setPrefColumns(3);
        fieldsPane.getChildren().addAll(userName, usernameField, password, passwordField,repeatPassword,rePasswordField, email, emailField, masterKey, masterField);
        fieldsPane.setHgap(10);
        fieldsPane.setVgap(3);

        ToggleGroup group = new ToggleGroup();

        createUserToggleButton = ProjectControls.createToggleButton("Create User", group, Double.MAX_VALUE, Double.MAX_VALUE);
        createUserToggleButton.setOnAction(e -> {
            if (createUserToggleButton.isSelected()) {
                //System.out.println(usernameField.getText());
                if (usernameField.getText().equalsIgnoreCase(null) || usernameField.getText().equalsIgnoreCase("")) {
                    System.out.println("Blank");
                    usernameField.requestFocus();
                }
                else if (passwordField.getText().equalsIgnoreCase(null) || usernameField.getText().equalsIgnoreCase("")) {
                    passwordField.requestFocus();
                }
                else if (masterField.getText().equalsIgnoreCase(null) || masterField.getText().equalsIgnoreCase("")){
                    masterField.requestFocus();
                }
                else if (emailField.getText().equalsIgnoreCase(null) || emailField.getText().equalsIgnoreCase("")){
                    emailField.requestFocus();
                }
                else if(!passwordField.getText().equalsIgnoreCase(rePasswordField.getText())){
                	new InfoMessage().messageBoxWithoutReset("Password Validation Failed.");
                	passwordField.requestFocus();
                }
                else {
                    System.out.println("Everything fine.");
                    userInfo.setUserName(usernameField.getText());
                    userInfo.setPassword(passwordField.getText());
                    userInfo.setMasterKey(masterField.getText());
                    userInfo.setEmail(emailField.getText());
                    new UserCreationConfirmationBox().messageBox("User ".concat(usernameField.getText()));
                }
            }
        });
        createUserToggleButton.setMaxWidth(100);

        createAdminToggleButton = ProjectControls.createToggleButton("Create Admin", group, Double.MAX_VALUE, Double.MAX_VALUE);
        createAdminToggleButton.setOnAction(e -> {
            if (createAdminToggleButton.isSelected()) {
                if (usernameField.getText().equalsIgnoreCase(null) || usernameField.getText().equalsIgnoreCase("")) {
                    System.out.println("Blank");
                    usernameField.requestFocus();
                }
                else if (passwordField.getText().equalsIgnoreCase(null) || passwordField.getText().equalsIgnoreCase("")) {
                    passwordField.requestFocus();
                }
     
                else if (emailField.getText().equalsIgnoreCase(null) || emailField.getText().equalsIgnoreCase("")){
                    emailField.requestFocus();
                }
                else if(!passwordField.getText().equalsIgnoreCase(rePasswordField.getText())){
                	new InfoMessage().messageBoxWithoutReset("Password Validation Failed.");
                	passwordField.requestFocus();
                }
                else {
                    userInfo.setUserName(usernameField.getText());
                    userInfo.setPassword(passwordField.getText());
                    userInfo.setEmail(emailField.getText());
                    new AdminConfirmationBox().messageBox("User ".concat(usernameField.getText()));
                }
            }
        });
        createAdminToggleButton.setMaxWidth(100);
        Region region = new Region();
        //ProjectMainLayout.mainPane().setCenter(null);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(createUserToggleButton, region, createAdminToggleButton);
        buttonBox.setMaxWidth(450);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        mainScreen = new VBox();
        mainScreen.getChildren().addAll(fieldsPane, buttonBox);
        ProjectMainLayout.mainPane().setCenter(mainScreen);
    }

    public static StoreNewUserInfo getUserInfo() {
        return userInfo;
    }
    public static void reset(){
        if(usernameField != null || passwordField !=null || masterField != null ) {
            usernameField.clear();
            passwordField.clear();
            masterField.clear();
            emailField.clear();
            createUserToggleButton.setSelected(false);
            createAdminToggleButton.setSelected(false);
        }
    }
}
