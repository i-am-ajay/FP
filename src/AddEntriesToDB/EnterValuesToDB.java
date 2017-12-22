package AddEntriesToDB;

/**
 * Created by gaa8664 on 3/2/15.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;
import javax.sql.*;

public class EnterValuesToDB {
    public void enterToDb(){
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.createConnection();
            PreparedStatement stmt=null;
            ProcessRecord processRecord=new ProcessRecord();

            //BufferedReader stream=new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/Software/java data/Downloads/data.dat"))));
            BufferedReader stream=new BufferedReader(new InputStreamReader(new FileInputStream(new File("files/data.dat"))));
            String value=null;
            List<String> list=null;
            Statement statement=con.createStatement();
            statement.executeUpdate("truncate table testtable");
            while((value=stream.readLine())!=null){
               list= processRecord.processRecord(value);
                System.out.println(list.size());
                PreparedStatement prestmt=con.prepareStatement("insert into testtable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                for(int i=0;i<list.size();i++){
                    prestmt.setString(i+1,list.get(i));
                }
                prestmt.executeUpdate();
                prestmt.close();
            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
