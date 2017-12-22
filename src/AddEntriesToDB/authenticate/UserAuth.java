package AddEntriesToDB.authenticate;

import AddEntriesToDB.DBConnection;
import globals.SenderEmailConf;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 6/12/2016.
 */
public class UserAuth implements Authentication {
    Connection con;
    public UserAuth(){
        con = DBConnection.getConnection();
    }
    @Override
    public boolean authenticate(String username, String password) {
        CallableStatement stmt = null;
        boolean flag = false;
        try {
            stmt = con.prepareCall("call user_login(?,?,?,?)");
            stmt.setString(1,username);
            stmt.setString(2,convertToHash(password));
            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.execute();
            flag = stmt.getBoolean(3);
            SenderEmailConf.email = stmt.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return flag;
    }
}
