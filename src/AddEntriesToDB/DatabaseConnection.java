package AddEntriesToDB;

/**
 * Created by gaa8664 on 3/2/15.
 */
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.sql.*;

public class DatabaseConnection {
    Properties prop;
    public Connection createConnection() throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","admin");
        return con;
    }
    public void createDatabase(){
        try{
            Connection con = createConnection();
            String query = getCreationQuery();
            System.out.println(query);
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.executeUpdate();

            System.out.println(query.length());
            System.out.println(query);
        }
        catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public String getCreationQuery(){
        String query="create table if not exists testTable ( ";
        List<String> list=new ArrayList<>();
        try {
            //BufferedReader reader=new BufferedReader( new InputStreamReader(new FileInputStream(new File("D:\\Software\\java data\\Downloads\\data.dat"))));
        	BufferedReader reader=new BufferedReader( new InputStreamReader(new FileInputStream(new File("files\\data.dat"))));
        	String str=reader.readLine().trim();
            StringTokenizer tokenizer=new StringTokenizer(str," ");
            while(tokenizer.hasMoreElements()){
                String token=tokenizer.nextToken();
                StringTokenizer subToken=new StringTokenizer(token,"=");
                String [] stringArray=subToken.nextToken().split("'");
                String temp=stringArray[1];
                if(temp!=" ")
                 list.add(temp);
            }
            int counter=0;
            for(String stri: list){
                System.out.printf("%d : %s%n",counter++,stri);
            }
            boolean flag=false;
            System.out.println(list.size());
            for (String queryPart:list){
                if(flag==false){
                    query+=queryPart+ " varchar(20)";
                    flag=true;
                }
                else{
                    if(queryPart.equalsIgnoreCase("default")){
                        queryPart="`"+queryPart+"`";
                    }
                    query += ", " + queryPart + " varchar(20)";

                }

            }
            query+=");";
            //System.out.println(query);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return query;
    }
}
