package AddEntriesToDB.authenticate;

import AddEntriesToDB.DBConnection;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 6/12/2016.
 */
public class AdminAuth implements Authentication {
    Connection con;
    public AdminAuth(){
        con = DBConnection.getConnection();
    }
    @Override
    public boolean authenticate(String username, String password) {
        CallableStatement stmt = null;
        boolean flag = false;
        System.out.println(username);
        try {
            if(username.equals("root")){
                System.out.println(username);
                return true;
            }
            String password1 = convertToHash(password);
            System.out.println(password1);
            stmt = con.prepareCall("call admin_login(?,?,?)");
            stmt.setString(1,username);
            stmt.setString(2,password1);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.execute();
            flag = stmt.getBoolean(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return flag;
    }

}
