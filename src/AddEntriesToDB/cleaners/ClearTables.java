package AddEntriesToDB.cleaners;

import AddEntriesToDB.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ajay on 6/11/2016.
 */
public class ClearTables {
    Connection con;
    String query;
    public ClearTables() throws SQLException {
        con = DBConnection.getConnection();
    }
    public boolean removeUserInfoFromTables() throws SQLException{
        CallableStatement stmt = con.prepareCall("call clear_tables()");
        stmt.executeUpdate();
        return true;
    }
}
