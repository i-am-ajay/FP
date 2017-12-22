package sensitive.rule;

import AddEntriesToDB.AddRuleToDB;
import AddEntriesToDB.CryptRules;
import AddEntriesToDB.ReadFile;
import AddEntriesToDB.RulesColumnInfo;
import globals.ParameterCriticalValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * This class create calculate the weight of rules and than add sensitive rules to DB, after encrypting them.
 */
public class FindRulesWeight {
    private static Map<String,String> masterMap = new HashMap<>();
    private CryptRules cryptRule = new CryptRules();
    private AddRuleToDB ruleToDB = new AddRuleToDB();
    private ReadFile readFile;
    public void readRules(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files\\output.text"));
            String str;
            Map<String,Double> ruleMap = new HashMap<>();
            // Create maps and data to calculate weight related info
            /*(ReadFile readFile=new ReadFile();
            readFile.init();*/
            readFile = ReadFile.getObject();
            readFile.init();
            while((str= reader.readLine())!=null){
                StringTokenizer tokenizer=new StringTokenizer(str," ");
                int i=tokenizer.countTokens();
                int count=1;
                String [] strArray;

                FindWeight weight=readFile.getWeightObject();
                double d = 0.0;
                while(tokenizer.hasMoreTokens()){

                    if(count!=i){
                        String stri=tokenizer.nextToken();
                        String strin=stri.replaceAll("'","");
                        strArray=strin.split("=");
                        String key=weight.getMasterMap().get(strArray[0]);
                        if(key.equalsIgnoreCase("integer")){
                            int [] intArray=weight.getIntAttributeValue(strArray[0]);
                            d += WeightForIntValues.calculateWeight(intArray[0],intArray[1],Integer.parseInt(strArray[1]),ParameterCriticalValue.getCriticalValueMap(strArray[0]));
                            //System.out.println("$"+strArray[0]+"$"+WeightForIntValues.calculateWeight(intArray[0],intArray[1],Integer.parseInt(strArray[1])));
                        }
                        else if(key.equalsIgnoreCase("string")){
                            d += WeightForStringValues.stringValues(strArray[1]);
                            //System.out.println("$"+strArray[0]+"$"+WeightForStringValues.stringValues(strArray[1]));
                        }
                        //System.out.println(strArray[0]+":"+strArray[1]);
                        count++;

                    }
                    else{
                        String stri=tokenizer.nextToken();
                        String[] strarr=stri.split("'");
                        String [] strarr1=strarr[1].split("=");
                        String key=weight.getMasterMap().get(strarr1[0]);
                        if(key == null){
                            continue;
                        }
                        if(key.equalsIgnoreCase("integer")){
                        	String value = strarr1[0];
                            int [] intArray=weight.getIntAttributeValue(value);
                            try {
                                d += WeightForIntValues.calculateWeight(intArray[0], intArray[1], Integer.parseInt(strarr[1]),ParameterCriticalValue.getCriticalValueMap(value));
                            }
                            catch(Exception ex){

                            }
//                            System.out.println("$"+strarr1[0]+"$"+WeightForIntValues.calculateWeight(intArray[0],intArray[1],Integer.parseInt(strarr[1])));
                        }
                        else if(key.equalsIgnoreCase("string")){
                            //System.out.println("$"+strarr1[0]+"$"+WeightForStringValues.stringValues(strarr1[1]));
                            d += WeightForStringValues.stringValues(strarr1[1]);
                        }
                    }
                }
                ruleMap.put(str,d);
            }
            int count = 0;
            for(String rule: ruleMap.keySet()){
                count++;
                double weight = ruleMap.get(rule);
                if(weight > 0.7){
                    // method will add rule info in table.
                    RulesColumnInfo.ruleToColMapping(count,rule);
                    byte [] ar=cryptRule.encrypt(rule);
                    ruleToDB.insertToDB(count,ar);
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String [] args){
        FindRulesWeight weight = new FindRulesWeight();
        weight.readRules();
        printFrequencyMaps(weight.readFile);
    }
    
    public static void printFrequencyMaps(ReadFile fileReader){
    	System.out.println(fileReader.getParameterMap());
    	System.out.println(fileReader.getDoubleFreqMap());
    	System.out.println(fileReader.getIntegerFreqMap());
    	System.out.println(fileReader.getStringFreqMap());
    	
    }
    
}
