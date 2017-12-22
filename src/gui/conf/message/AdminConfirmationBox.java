package gui.conf.message;

import AddEntriesToDB.CreateAdmin;
import globals.StoreNewUserInfo;
import gui.AssignKeyWithPermission;
import gui.CreateUser;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import keys.AuthKey;

import java.sql.SQLException;

/**
 * Created by ajay on 15-Nov-16.
 */
public class AdminConfirmationBox extends ConfirmationBox {
    @Override
    public void messageBox(String user){
        super.messageBox(user);
        super.getOkButton().setOnAction(e -> {
            stage.close();
            boolean flag = false;
            try {
                CreateAdmin newAdmin = new CreateAdmin();
                StoreNewUserInfo userInfo = new StoreNewUserInfo();
                //System.out.println("VAlue of flag is"+flag);
                flag = newAdmin.insertAdminInTable(CreateUser.getUserInfo().getUserName(), CreateUser.getUserInfo().getPassword(), CreateUser.getUserInfo().getEmail());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("flag is true" + flag);

            if (flag) {
                new InfoMessage().messageBox("Admin Already exists");
                ProjectMainLayout.mainPane().setCenter(CreateUser.mainScreen);
                ProjectLables.stringForSubLabel.setValue("Create User/Admin");
            }
            stage.close();
        });
    }
}
