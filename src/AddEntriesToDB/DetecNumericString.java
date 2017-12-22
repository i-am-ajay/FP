package AddEntriesToDB;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gaa8664 on 3/19/15.
 */
class DetectNumericString {
    public static String detectNumericString(String str){
        try {
            Integer.parseInt(str);

        }
        catch(Exception e){
            try {
                Double.parseDouble(str);
                return "Double";
            }
            catch(Exception ex){
                return "String";
            }
        }
        return "Integer";
    }
}
