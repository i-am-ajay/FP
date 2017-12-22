package AddEntriesToDB;

import globals.SenderEmailConf;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static generatehash.ConvertToHash.convertToHash;

/**
 * Created by ajay on 14-Nov-16.
 */
public class GetKeysUsingMaster {
    Connection con;
    String query;
    ResultSet resultSet;
    public GetKeysUsingMaster(){
        con = DBConnection.getConnection();
    }
    public List<String> getKeyUsingMaster(String user, String key){
        List<String> keys = null;
        CallableStatement callback = null;
        try {
            callback = con.prepareCall("call RetrieveKeys_usingMasterKey (?,?,?)");
            callback.setString(1, user);
            callback.setString(2, key);
            callback.registerOutParameter(3, Types.VARCHAR);
            callback.executeUpdate();
            //System.out.println("98398379483"+numOfResultSets);
            SenderEmailConf.email = callback.getString(3);
            resultSet = callback.getResultSet();
            keys = getKeys(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keys;
    }
    private List<String> getKeys(ResultSet resultSet) throws SQLException {
        List<String> keys = new ArrayList<>();
        while(resultSet.next()){
            keys.add(resultSet.getString(1));
        }
        return keys;
    }
}
