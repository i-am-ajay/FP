package gui;

import AddEntriesToDB.cleaners.ClearUserData;
import AddEntriesToDB.SaveNewPermission;
import globals.GlobalParameters;
import globals.StoreNewUserInfo;
import gui.conf.ProjectControls;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.message.InfoMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.TreeSet;

/**
 * Created by ajay on 6/6/2016.
 */
public class AssignKeyWithPermission {
    private static TextField keyField;
    private static String permissions;
    private static StoreNewUserInfo userInfo;
    private static TreeSet<String> permissionSet;
    public static void userKeyWithPermission(){
        ProjectLables.stringForSubLabel.setValue("Key with Permission.");
        permissionSet = new TreeSet<>();
        userInfo = CreateUser.getUserInfo();
        Label keyLabel = ProjectLables.labelForFields("Key");
        keyField = new TextField();
        keyField.setEditable(false);
        keyField.setPrefWidth(200);
        keyLabel.setLabelFor(keyField);


        Region region = new Region();
        Button saveUserButton = ProjectControls.createButton("Save User");
        saveUserButton.setPrefWidth(200);
        saveUserButton.setOnAction(e -> {
            userInfo.setKey(keyField.getText());
            userInfo.setPermission(getKeyPermission());
            SaveNewPermission newPermission = null;
            try {
                newPermission = new SaveNewPermission();
                newPermission.insertNewPermissionInTable(userInfo.getPermission());
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            new InfoMessage().messageBox("User Created Successfully..");
            ProjectLables.stringForSubLabel.setValue(AdminScreen.subLevelTitle);
            ProjectMainLayout.mainPane().setCenter(AdminScreen.box);
        });
        Button backButton = ProjectControls.createButton("Back");
        backButton.setPrefWidth(200);
        backButton.setOnAction(e->{
            try {
                new ClearUserData().removeUserInfoFromTables(userInfo.getUserName(),keyField.getText());
                ProjectMainLayout.mainPane().setCenter(CreateUser.mainScreen);
                ProjectLables.stringForSubLabel.setValue("Create User/Admin");
                CreateUser.reset();
                LaunchApp.mainStage.setOnCloseRequest( es->{

                });
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        VBox keyBox = new VBox(5,keyLabel,keyField,saveUserButton,backButton);
        keyBox.setPadding(new Insets(5, 10, 5, 10));
        keyBox.setAlignment(Pos.TOP_LEFT);

        Label permissionLabel = ProjectLables.labelForFields("Set Permissions");
        ObservableList<CheckBox> listOfCheckBoxes = FXCollections.observableArrayList();
        for(String str : GlobalParameters.getRulesParameter()){
            CheckBox box = ProjectControls.createCheckBox(str);
            box.setUserData(str);
            box.setOnAction( e->{
                if(box.isSelected()){
                    permissionSet.add((String)box.getUserData());
                }
                else if(!box.isSelected()){
                    permissionSet.remove((String)box.getUserData());
                }
            });
            listOfCheckBoxes.add(box);
        }
        VBox permissionBox = new VBox();
        permissionBox.getChildren().addAll(listOfCheckBoxes);
        permissionBox.setPadding(new Insets(2,5,2,5));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(200);
        scrollPane.setMaxWidth(200);
        scrollPane.setContent(permissionBox);

        VBox permissionScreen = new VBox(3,permissionLabel,scrollPane);
        HBox mainScreen = new HBox(10);
        mainScreen.getChildren().addAll(keyBox,permissionScreen);
        mainScreen.setAlignment(Pos.CENTER);
        mainScreen.setPadding(new Insets(10));
        mainScreen.setPrefWidth(Double.MAX_VALUE);
        mainScreen.setMaxWidth(Double.MAX_VALUE);
        ProjectMainLayout.mainPane().setCenter(mainScreen);
        LaunchApp.mainStage.setOnCloseRequest( e->{
            e.consume();
        });
    }
    public static void setKeyField(String key){
        keyField.setText(key);
    }
    public static String getKeyPermission(){
        String permission = "";
        for(String str : permissionSet){
            permission = permission.concat(", ".concat(str));
        }
        int index = permission.indexOf(',');
        permission = permission.substring(index+1,permission.length()).trim();
        return permission;
    }
}
