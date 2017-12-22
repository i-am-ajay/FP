package AddEntriesToDB;

import java.sql.Connection;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by ajay on 2/7/2016.
 */
public class CreateDBPref {
    Preferences pref;
    public void createDBPrefrences(){
        pref = Preferences.userNodeForPackage(Connection.class);
        //System.out.println(pref.absolutePath());
        pref.put("url","jdbc:mysql://localhost:3306");
        pref.put("username","root");
        pref.put("password","admin");
    }
    public String getPref(String name){
        return pref.get(name,"");
    }


    public static void main(String [] args){
        CreateDBPref pref = new CreateDBPref();
        pref.createDBPrefrences();
        System.out.println(pref.getPref("username"));

    }
}
