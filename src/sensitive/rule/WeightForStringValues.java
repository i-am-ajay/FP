package sensitive.rule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by gaa8664 on 5/19/15.
 */
class WeightForStringValues {
    static Map<String,Double> weightMap=new HashMap<String,Double>();
    public WeightForStringValues(){
        try {
            setWeight();
        }
        catch(IOException e){
            e.getStackTrace();
        }
    }

    public void setWeight() throws IOException {
        Properties pro=new Properties();
        pro.load(new FileInputStream("files\\values.properties"));
        Enumeration enu=pro.propertyNames();
        while(enu.hasMoreElements()){
            String val=enu.nextElement().toString();
            double weight=Double.parseDouble(pro.getProperty(val));
            weightMap.put(val,weight);
        }
    }
    public static  double stringValues(String value){
       return weightMap.get(value);
    }

    }
