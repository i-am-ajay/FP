package AddEntriesToDB;

import java.io.IOException;

/**
 * Created by ajay on 2/7/2016.
 */
public class TestCrypto  {
    public static void main(String [] args){
        CryptRules rules = new CryptRules();
        try {
            byte [] b=rules.encrypt("Hello World");
            System.out.println(new String());
            String s =new String(b);
            System.out.println(s);
            //s=s.trim();

            //System.out.println(b.length);
            String str = rules.decrypt(b);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
