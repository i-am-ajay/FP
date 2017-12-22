package AddEntriesToDB;

import gui.DataTable;
import gui.RuleClass;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


/**
 * Created by ajay on 2/8/2016.
 */
public class GetRuleFromDB {
    Connection con;
    String ruleQuery;
    public GetRuleFromDB(){
        con = DBConnection.getConnection();
    }
    public boolean readRuleQuery(String username,String key, String query) {
        boolean flag = false;
        try {
            CallableStatement stm = con.prepareCall("call retrive_rules(?,?,?)");
            stm.setString(1, username);
            stm.setString(2, key);
            stm.registerOutParameter(3, Types.VARCHAR);
            stm.execute();
            ruleQuery = stm.getString(3);
            if (ruleQuery != null) {
                flag = true;
            }
            System.out.println(flag);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public void readRules(){
        try{
        PreparedStatement prep = con.prepareStatement(ruleQuery);
        ResultSet set = prep.executeQuery();
        while(set.next()) {
            byte[] b = set.getBytes(1);
            CryptRules rule = new CryptRules();
            DataTable.insertToList(new RuleClass(rule.decrypt(b)));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static void main(String [] args){
        GetRuleFromDB db = new GetRuleFromDB();
        //db.readRule();
    }
}
