package AddEntriesToDB;

import java.io.IOException;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

import globals.ParametersOfDataset;

public class ParametersToDB {
	static {
		con = DBConnection.getConnection();
		main();
	};
	static Connection con;
	public static void addParametersToDb(List<String> paramList) throws SQLException {
		CallableStatement parameter_flag = con.prepareCall("call if_parameter_exists(?)");
		parameter_flag.registerOutParameter(1, Types.BOOLEAN);
		parameter_flag.executeQuery();
		boolean flag = parameter_flag.getBoolean(1);
		if(!flag) {
			con.setAutoCommit(false);
			CallableStatement addParameter = con.prepareCall("call add_parameter(?)");
			for(String parameter : paramList) {
				addParameter.setString(1, parameter);
				addParameter.addBatch();
			}
			addParameter.executeBatch();
			con.commit();
			con.setAutoCommit(true);
		}
		else {
			System.out.println("Parameter Exists");
		}
	}
	
	public static List<String> getParameters() throws SQLException{
		PreparedStatement prepStatement  = con.prepareStatement("SELECT parameter FROM rulesdb.parameters");
		ResultSet parameterResultSet = prepStatement.executeQuery();
		List<String> paramList = new ArrayList<>();
		while(parameterResultSet.next()) {
			paramList.add(parameterResultSet.getString(1));
		}
		return paramList;
		
	}
	
	public static String[] getParameterValues(String parameter){
		String [] paramArray = null;
		CallableStatement statement = null;
		try {
			statement = con.prepareCall("call get_parameter_values(?,?,?)");
			statement.setString(1, parameter);
			statement.registerOutParameter(2, Types.DECIMAL);
			statement.registerOutParameter(3, Types.VARCHAR);
			ResultSet parameterData = statement.executeQuery();
			Double val = statement.getDouble(2);
			String weight = val.toString();
			String remark = statement.getString(3);
			paramArray = new String[]{weight,remark};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramArray;
	}
	
	public static void updateWeight(String parameter, String weight) {
		try {
			CallableStatement updateWeight = con.prepareCall("call update_parameter_weight(?,?)");
			updateWeight.setString(1, parameter);
			updateWeight.setDouble(2, Double.parseDouble(weight));
			updateWeight.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateRemarks(String parameter, String remarks) {
		try {
			CallableStatement updateRemarks = con.prepareCall("call update_parameter_remarks(?,?)");
			updateRemarks.setString(1, parameter);
			updateRemarks.setString(2, remarks);
			updateRemarks.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main() {
		try {
			List<String> list = ParametersOfDataset.getParameters();
			addParametersToDb(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Map<String,Double> getCriticalValueMap(){
		Map<String,Double> map = new HashMap<>();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT Parameter,weight FROM Parameters");
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				map.put(set.getString(1), set.getDouble(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}	
