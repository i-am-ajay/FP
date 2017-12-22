package gui.conf.message;

import AddEntriesToDB.SaveUser;
import globals.StoreNewUserInfo;
import gui.AssignKeyWithPermission;
import gui.CreateUser;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.message.ConfirmationBox;
import gui.conf.message.InfoMessage;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import keys.AuthKey;

import java.sql.SQLException;

/**
 * Created by ajay on 6/6/2016.
 */
public class UserCreationConfirmationBox extends ConfirmationBox {
	VBox mainScreen;
    @Override
    public void messageBox(String user){
        super.messageBox(user);
        super.getOkButton().setOnAction(e -> {
            stage.close();
            String flag = "";
            String masterKey = CreateUser.getUserInfo().getMasterKey();
            System.out.println(masterKey);
            try {
                SaveUser newuser = new SaveUser();
                StoreNewUserInfo userInfo = new StoreNewUserInfo();
                System.out.println("Value of flag is"+flag);
                flag = newuser.insertUserInTable(CreateUser.getUserInfo().getUserName(), CreateUser.getUserInfo().getPassword(), CreateUser.getUserInfo().getEmail(), masterKey);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("flag is true" + flag);

            if (flag.equalsIgnoreCase("us")) {
                new InfoMessage().messageBox("Username Already exists");
                ProjectMainLayout.mainPane().setCenter(CreateUser.mainScreen);
                ProjectLables.stringForSubLabel.setValue("Create User/Admin");

            }
            else if (flag.equalsIgnoreCase("ma")) {
                new InfoMessage().messageBox("Master Key Already assigned");
                ProjectMainLayout.mainPane().setCenter(CreateUser.mainScreen);
                ProjectLables.stringForSubLabel.setValue("Create User/Admin");
            } else {
                String key = new AuthKey().generateKey();
                AssignKeyWithPermission.userKeyWithPermission();
                AssignKeyWithPermission.setKeyField(key);
            }
            stage.close();
        });
    }
}
