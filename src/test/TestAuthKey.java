package test;

import AddEntriesToDB.DBConnection;
import keys.AuthKey;

import java.sql.SQLException;

/**
 * Created by ajay on 4/20/2016.
 */
public class TestAuthKey {

    public static void main(String [] args){
        try {
            DBConnection.connection("rulesdb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AuthKey key = new AuthKey();
        key.generateKey();
    }
}
