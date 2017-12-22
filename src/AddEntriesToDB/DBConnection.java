package AddEntriesToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 * Created by ajay on 2/7/2016.
 */
public class DBConnection {
    static Connection con;
    public static Connection connection(String dbName) throws SQLException {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(con == null) {
            Preferences pref = Preferences.userNodeForPackage(Connection.class);
            String url = pref.get("url", "");
            url = url.concat("/rulesdb");
            System.out.println(url);
            String userName = pref.get("username", "");
            System.out.println(userName);
            String password = pref.get("password", "");
            con = DriverManager.getConnection(url, userName, password);
        }
        return con;
    }
    public static Connection getConnection(){
        if(con == null)
            try {
                connection(null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return con;
    }
}
