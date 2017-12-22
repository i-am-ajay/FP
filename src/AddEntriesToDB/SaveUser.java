package AddEntriesToDB;

import globals.StoreNewUserInfo;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 6/7/2016.
 */
public class SaveUser{
    Connection con;
    String query;
    public SaveUser() throws SQLException {
        con = DBConnection.getConnection();
    }
    public String insertUserInTable(String userName,String password, String email, String key) throws SQLException {
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall("call create_unique_user(?,?,?,?,?)");
            stmt.setString(1, userName);
            stmt.setString(2, convertToHash(password));
            stmt.setString(3, email);
            stmt.setString(4, key);
            stmt.registerOutParameter(5, Types.CHAR);
            stmt.executeUpdate();
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return stmt.getString(5);
    }
}
