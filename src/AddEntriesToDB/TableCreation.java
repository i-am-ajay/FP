package AddEntriesToDB;

import globals.GlobalParameters;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;

/**
 * Created by ajay on 6/5/2016.
 */
public class TableCreation {
    static Connection con;
    static{
        try {
            con = DBConnection.connection(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean createUserTable() throws SQLException {
        String userTableCreationQuery = "Create table if not exists UserTable ( id int auto_increment, username varchar(50),`password` varchar(100),primary key(id))";
        Statement userTableCreationStatement = con.createStatement();
        boolean flag = userTableCreationStatement.execute(userTableCreationQuery);
        return flag;
    }
    public static boolean createAdminTable() throws SQLException {
        String adminTableCreationQuery = "Create table if not exists AdminTable(id int auto_increment primary key, adminname varchar(50),`password` varchar(100))";
        Statement adminTableCreationStatement = con.createStatement();
        boolean flag = adminTableCreationStatement.execute(adminTableCreationQuery);
        return flag;
    }
    public static boolean createKeyPermissionTable() throws SQLException {
        String keyPermissionTable = "Create table if not exists KeyPermission(id int auto_increment primary key,permission varchar(500))";
        Statement keyPermissionStatement = con.createStatement();
        boolean flag = keyPermissionStatement.execute(keyPermissionTable);
        return flag;
    }
    public static boolean permisisonBasedQueryPool() throws SQLException {
        String createPoolQuery = "Create table if not exists QueryPool (permissionId int, query varchar(2000))";
        Statement createPoolStatement = con.createStatement();
        boolean flag = createPoolStatement.execute(createPoolQuery);
        return flag;
    }
    public static boolean keyToPermissionMappintTable() throws SQLException {
        String query = "create table if not exists permissionkeymappingtable( keyId int, permission_Id int)";
        Statement createMappingTable = con.createStatement();
        boolean flag = createMappingTable.execute(query);
        return flag;
    }
    public static void createRulesColumnTable() throws SQLException {
        String query = "create table if not exists rulesColumnTable ( id int," ;
        Collection<String> ruleColSet = GlobalParameters.getRulesParameter();
        for(String col : ruleColSet){
            query = query.concat("`".concat(col.concat("` varchar(50),")));
        }
        query = query.substring(0, query.length() - 1);
        query = query.concat(" )");
        Statement createRulesColumnTableStmt = con.createStatement();
        boolean flag = createRulesColumnTableStmt.execute(query);
    }
    public static boolean userKeyMappingTable() throws SQLException{
        String query = "create table if not exists userkeyMapping ( userid int, keyid int )";
        Statement createMappingTable = con.createStatement();
        boolean flag = createMappingTable.execute(query);
        return flag;

    }
    public static boolean createRulesTable() throws SQLException{
        String query = "create table if not exists rulesTable ( id int, rules blob )";
        Statement createRulesTable = con.createStatement();
        boolean flag = createRulesTable.execute(query);
        return flag;
    }
    public static boolean createUserKeyTable() throws SQLException {
        String query = "create table if not exists userKeys ( id int auto_increment primary key, key char(20) )";
        Statement createKeyTable = con.createStatement();
        boolean flag = createKeyTable.execute(query);
        return flag;
    }
    public static void init(){
        try {
            createUserTable();
            createAdminTable();
            createKeyPermissionTable();
            createRulesColumnTable();
            permisisonBasedQueryPool();
            keyToPermissionMappintTable();
            userKeyMappingTable();
            createRulesTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
