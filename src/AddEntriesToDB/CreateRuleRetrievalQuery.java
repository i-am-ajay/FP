package AddEntriesToDB;

import java.sql.*;

/**
 * Created by ajay on 6/5/2016.
 */
public class CreateRuleRetrievalQuery {
    String permission;
    int permissionId;
    static Connection con;
    String query;
    public static String retrieveRules(String permission){
        String query = null;
            String [] columns = permission.split(",");
            query = "Select rules from rulesTable inner join rulescolumntable on Id = count where ";
            for(String str : columns){
                str = "( `".concat(str.trim().concat("` <> 1 OR `")).concat(str.trim()).concat("` is Null )");
                query = query.concat(str).concat(" and ");
            }
            int lastIndexOfAnd = query.lastIndexOf("and");
            query = query.substring(0, lastIndexOfAnd);
            System.out.println(query);
        return query;
    }

    public static void storeQuery(String permission,int permissionId){
        try {
            con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into querypool (permissionid,query) values (?,?)");
            stmt.setInt(1,permissionId);
            stmt.setString(2,retrieveRules(permission));
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
