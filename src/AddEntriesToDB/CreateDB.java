package AddEntriesToDB;

import java.sql.*;

/**
 * Created by ajay on 2/7/2016.
 */
public class CreateDB {
    public static void main(String [] args){
        new CreateDB().createDB();
    }
    public void createDB(){
        Connection con;
        try {
            DBConnection.connection("");
            con = DBConnection.getConnection();
            Statement dbStatement = con.createStatement();
            dbStatement.execute("create schema rulesDB");
            dbStatement.execute("use rulesDB");
            dbStatement.execute("create table rulesTable (id int ,rules blob)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
