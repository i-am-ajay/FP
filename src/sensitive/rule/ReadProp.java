package sensitive.rule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by gaa8664 on 5/19/15.
 */
public class ReadProp {
    public static void main(String [] args) throws IOException {
        Properties prop=new Properties();
        //prop.load(new FileInputStream("G:\\projects\\meenakshi ma'am\\files\\properties.prop"));
        prop.load(new FileInputStream("files\\properties.prop"));
        prop.list(System.out);
        System.out.println(prop.getProperty("good"));

    }

}
