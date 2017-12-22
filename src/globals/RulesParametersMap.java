package globals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by ajay on 6/6/2016.
 */
public class RulesParametersMap {
    static Map<Integer,String> rulesParameterMap;
    public static Map<Integer,String> createMap(){
        rulesParameterMap= new HashMap<>();
        String rule = null;
        try {
            rule = GlobalParameters.getRulesFileReader().readLine();
            processRecordFillMap(rule);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rulesParameterMap;
    }
    public static void processRecordFillMap(String rule){
        StringTokenizer tokenizer = new StringTokenizer(rule," ");
        int count = 0;
        while(tokenizer.hasMoreTokens()){
            String[] colArray = tokenizer.nextToken().split("=");
            String value= colArray[1].substring(0,colArray[1].length()-1);
            // find column name against column number.create a map of rules parameters
                rulesParameterMap.put(count,colArray[0].substring(1));
                count++;
            }
    }
}
