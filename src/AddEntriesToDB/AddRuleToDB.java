package AddEntriesToDB;

import java.sql.*;

/**
 * Created by ajay on 2/7/2016.
 */
public class AddRuleToDB {
    Connection con;
    public AddRuleToDB(){
        try {
            DBConnection.connection("rulesDB");
            con = DBConnection.getConnection();
            clearTable(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertToDB(int id,byte [] array){
        try {
            Statement stm = con.createStatement();
            stm.execute("use rulesDB");
            PreparedStatement prepStm = con.prepareStatement("replace into rulesTable (id,rules) values (?,?)");
            prepStm.setInt(1,id);
            prepStm.setBytes(2, array);
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearTable(Connection con){
        try {
            String clearTableQuery = "truncate table rulesTable";
            Statement clearTableStatement = con.createStatement();
            clearTableStatement.execute(clearTableQuery);
        }
        catch(SQLException ex){
            return;
        }
    }
}