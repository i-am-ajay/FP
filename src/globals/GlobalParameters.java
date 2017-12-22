package globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ajay on 6/6/2016.
 */
public final class GlobalParameters {
    static Set<String> rulesParameter;
    static BufferedReader rulesFileReader;
    static Map<Integer,String> rulesParameterMap;

    public static void setRulesParameter(){
        rulesParameter = new HashSet<String>();
        for(String parameter: rulesParameterMap.values()){
            rulesParameter.add(parameter);
        }
    }
    public static Set<String> getRulesParameter(){
        return rulesParameter;
    }

    public static void setRulesFileReader(String filePath){
        if(filePath == null){
            //filePath = "G:\\projects\\meenakshi ma'am\\files\\data.dat";
        	filePath = "files\\data.dat";
        }
        File rulesFile = new File(filePath);
        try {
            rulesFileReader = new BufferedReader(new FileReader(new File(filePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static BufferedReader getRulesFileReader(){
        return rulesFileReader;
    }

    public static void setRulesParameterMap(){
        rulesParameterMap = RulesParametersMap.createMap();
    }
    public static Map<Integer,String> getRulesParameterMap(){
        return rulesParameterMap;
    }
}
