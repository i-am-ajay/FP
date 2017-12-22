package AddEntriesToDB;

import globals.StoreNewUserInfo;
import gui.CreateUser;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 6/9/2016.
 */
public class SaveNewPermission {
    Connection con;
    String query;
    public SaveNewPermission() throws SQLException {
        con = DBConnection.getConnection();
    }
    public int insertNewPermissionInTable(String permission) throws SQLException{
        CallableStatement stmt = null;
        stmt = con.prepareCall("call key_to_permission_mapping(?,?,?,?)");
        StoreNewUserInfo userInfo = CreateUser.getUserInfo();
        stmt.setString(1, userInfo.getUserName());
        stmt.setString(2, userInfo.getKey());
        stmt.setString(3, userInfo.getPermission());
        stmt.registerOutParameter(4, Types.INTEGER);
        stmt.executeUpdate();
        int permissionId = stmt.getInt(4);
        if (permissionId != 0) {
            System.out.println(permissionId);
            CreateRuleRetrievalQuery.storeQuery(permission, permissionId);
        }

        return 1;
    }
}
