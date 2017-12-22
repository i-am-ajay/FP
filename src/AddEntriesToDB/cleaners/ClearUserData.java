package AddEntriesToDB.cleaners;

import AddEntriesToDB.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by ajay on 6/10/2016.
 */
public class ClearUserData {
    Connection con;
    String query;
    public ClearUserData() throws SQLException {
        con = DBConnection.getConnection();
    }
    public void removeUserInfoFromTables(String userName,String key) throws SQLException{
        CallableStatement stmt = con.prepareCall("call clear_user_data(?,?)");
        stmt.setString(1, userName);
        stmt.setString(2, key);
        stmt.executeUpdate();
    }
}
