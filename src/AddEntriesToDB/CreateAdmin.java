package AddEntriesToDB;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 15-Nov-16.
 */
public class CreateAdmin {
    Connection con;
    String query;
    public CreateAdmin() throws SQLException {
        con = DBConnection.getConnection();
    }
    public boolean insertAdminInTable(String userName,String password, String email) throws SQLException {
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall("call create_unique_admin(?,?,?,?)");
            stmt.setString(1, userName);
            stmt.setString(2, convertToHash(password));
            stmt.setString(3, email);
            stmt.registerOutParameter(4, Types.BOOLEAN);
            stmt.executeUpdate();

        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return stmt.getBoolean(4);
    }
}
