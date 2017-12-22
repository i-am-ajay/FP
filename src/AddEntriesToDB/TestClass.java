package AddEntriesToDB;

/**
 * Created by gaa8664 on 3/20/15.
 */
public class TestClass {
    public static void main(String [] args){
        String myString="Hello = Hi";
        String [] stringArray=myString.split("=");
        System.out.println(stringArray[0]);
        System.out.println(stringArray[1]);
    }
}
