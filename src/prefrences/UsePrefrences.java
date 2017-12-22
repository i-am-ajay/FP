package prefrences;
import java.util.prefs.Preferences;
/**
 * Created by gaa8664 on 5/6/15.
 */
public class UsePrefrences {
    public static void main(String [] args){
        setPref();
    }

    public static void setPref(){
        Preferences pref= Preferences.userRoot().node("d:\\");
        String prop1="Test1";
        String prop2 = "Test2";
        String prop3 = "Test3";
        System.out.println(pref.get(prop1,"First Prop not set."));
        System.out.println(pref.get(prop2,"Second Prop not set."));
        System.out.println(pref.get(prop3,"Third Prop not set."));

        pref.put(prop1,"String 1");
        pref.put(prop2,"String 2");
        pref.put(prop3,"String 3");
    }
}
