package AddEntriesToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

public class ParametersToDB {
	public static String[] getParameterValues(String parameter){
		Connection con = DBConnection.getConnection();
		String [] paramArray = null;
		CallableStatement statement = null;
		try {
			statement = con.prepareCall("");
			statement.registerOutParameter(2, Types.DECIMAL);
			statement.registerOutParameter(3, Types.VARCHAR);
			ResultSet parameterData = statement.executeQuery();
			String weight = statement.getString(2);
			String remark = statement.getString(3);
			paramArray = new String[]{weight,remark};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramArray;
	}
}	
