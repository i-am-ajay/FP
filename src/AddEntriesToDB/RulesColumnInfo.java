package AddEntriesToDB;

import globals.GlobalParameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RulesColumnInfo {
    private static Map<String,Boolean> rulesMappingMap;
    static Connection con;
    static{
        init();
    }

    static void init(){
        con = DBConnection.getConnection();
        rulesMappingMap = new HashMap<String,Boolean>();
        clearTable(con);
    }

    public static void createTable(Map<Integer,String> map) throws SQLException {
        String query = "create table if not exists rulesColumnTable ( id int," ;
        Collection<String> ruleColSet = map.values();
        for(String col : ruleColSet){
            rulesMappingMap.put(col,false);
           query = query.concat("`".concat(col.concat("` varchar(50),")));
        }
        query = query.substring(0,query.length()-1);
        query = query.concat(" )");
        executeQuery(query, true);
    }

    private static void executeQuery(String query,boolean isUpdate) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement prepStatement = con.prepareStatement(query);
        //System.out.println(query);
        if(isUpdate)
            prepStatement.executeUpdate();
        else
            prepStatement.executeQuery();

    }

    public static void ruleToColMapping(int count,String rule) throws SQLException {
        resetMap();
        String query = ruleProcessing(count, rule);
        executeQuery(query, false);
    }
    private static void resetMap(){
        for(String key : rulesMappingMap.keySet()){
            rulesMappingMap.put(key,false);
        }
    }
    private static String ruleProcessing(int count,String rule){
        StringTokenizer tokenizer = new StringTokenizer(rule," ");
        while(tokenizer.hasMoreTokens()){
            String processedRules = tokenizer.nextToken();
            processedRules = processedRules.replaceAll("'","");
            String [] colArray = processedRules.split("=");
            rulesMappingMap.put(colArray[0],true);
        }
        return createQuery(count);
    }
    private static String createQuery(int count){
        String firstHalf = null;
        String secondHalf = null;
        firstHalf = "replace into rulesColumnTable ( count, ";
        secondHalf = "( "+count +(", ");
        for(Map.Entry<String,Boolean> entry : rulesMappingMap.entrySet()){
            firstHalf = firstHalf.concat("`".concat(entry.getKey().concat("`, ")));
            secondHalf = secondHalf.concat(entry.getValue().toString().concat(", "));
        }
        firstHalf = firstHalf.substring(0,firstHalf.length()-2).concat(" )");
        secondHalf = secondHalf.substring(0,secondHalf.length()-2).concat(" )");
        return firstHalf.concat(" values ".concat(secondHalf));
    }

    public static void clearTable(Connection con){
        try{
            String clearTableQuery = "truncate table rulesTable";
            Statement clearTableStatement = con.createStatement();
            clearTableStatement.execute(clearTableQuery);
        }
        catch(SQLException ex){
            return;
        }

    }
}
