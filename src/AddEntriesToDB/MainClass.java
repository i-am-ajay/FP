package AddEntriesToDB;

/**
 * Created by gaa8664 on 3/2/15.
 */
public class MainClass {
    public static void main(String [] args){
        DatabaseConnection con1=new DatabaseConnection();
        try {
            con1.createDatabase();
            EnterValuesToDB enterToDB=new EnterValuesToDB();
            enterToDB.enterToDb();
        }
        catch(Exception e){

        }
    }
}
