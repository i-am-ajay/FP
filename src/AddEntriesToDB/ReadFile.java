package AddEntriesToDB;

import sensitive.rule.FindWeight;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class ReadFile{
    List <String> columnList;
    // Maps will store column numbers and set of values that are in those columns.
    Map<Integer,Set<Integer>> integerSetMap=new HashMap<>();
    Map<Integer,Set<Double>> doubleSetMap=new HashMap<>();
    Map<Integer,Set<String>> stringSetMap=new HashMap<>();
    Map<Integer,Integer> integerFreqMap = new HashMap<>();
    Map<Integer,Integer> doubleFreqMap = new HashMap<>();
    Map<Integer,Integer> stringFreqMap = new HashMap<>();
    FindWeight weight;

    List<Integer> stringCounterList=new ArrayList<>();
    List<Integer> integerCounterList=new ArrayList<>();
    List<Integer> doubleCounterList=new ArrayList<>();

    Map<Integer,String> map=new HashMap<>();
    List<Set<?>> listOfSet=new ArrayList<>();
    boolean rowFlag=false;
    // method to read the file.
    static ReadFile readFileObj;
    static{
        readFileObj=new ReadFile();
    }
    public void readFile() throws FileNotFoundException, IOException, SQLException {
        // read output file of fpgrowth.
        BufferedReader reader=new BufferedReader(new InputStreamReader( new FileInputStream(new File("files\\data.dat"))));
        String record=null;
        boolean flag=false;
        
        while((record=reader.readLine ())!=null){
            List<String> list;
            // call method that will process the record.
            list=processRecord(record,flag);
            flag=true;
        }
    }
    public  void init(){
        ReadFile file=readFileObj;
        try {
            file.readFile();
            file.printSet();
            weight=new FindWeight();
            weight.fillMap();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main (){

    }
    public static ReadFile getObject(){
        return readFileObj;
    }
    // Take a line from the file as parameter, process it to get values,
    // add values to the array list and return the list
    public List<String> processRecord(String record,boolean flag) throws SQLException {
    	
        columnList=new ArrayList<>();
        StringTokenizer tokenizer=new StringTokenizer(record," ");
        int switchFlag=0;
        int count=0;
        
        while(tokenizer.hasMoreTokens()){
        	
            String[] colArray = tokenizer.nextToken().split("=");
            String value= colArray[1].substring(0,colArray[1].length()-1);

            // find column name against column number.create a map of column headers
            if (flag == false) {
                map.put(count,colArray[0].substring(1));
                count++;    
            }
            // map of column values, key will be colum number and all possible values will be stored in set.
            else if(flag==true){
                // for first iteration in else block, maps key and values will be put.
                if(rowFlag==false){
                   String type= DetectNumericString.detectNumericString(value);
                    if(type.equalsIgnoreCase("String")) {
                        stringSetMap.put(count, new HashSet<String>());
                        stringFreqMap.put(count, 0);
                        stringCounterList.add(count);
                    }
                    else if(type.equalsIgnoreCase("Integer")) {
                        integerSetMap.put(count, new TreeSet<Integer>());
                        integerFreqMap.put(count, 0);
                        integerCounterList.add(count);
                    }
                    else if(type.equalsIgnoreCase("Double")){
                        doubleSetMap.put(count,new HashSet<Double>());
                        doubleFreqMap.put(count,0);
                        doubleCounterList.add(count);
                    }
                }
                if (stringCounterList.contains(count))
                    switchFlag=0;
                else if(integerCounterList.contains(count))
                    switchFlag=1;
                else if( doubleCounterList.contains(count))
                    switchFlag=2;
                // populate the set in the map. with the relevant values.
                // these values will be used to find the weight of the fp-growth rules.
                switch(switchFlag){
                    case 0:
                        stringSetMap.get(count).add(value);
                        stringFreqMap.put(count, stringFreqMap.get(count) + 1);
                        break;
                    case 1:
                        integerSetMap.get(count).add(Integer.parseInt(value));
                        integerFreqMap.put(count, (integerFreqMap.get(count)+1));
                        break;
                    case 2:
                        doubleSetMap.get(count).add(Double.parseDouble(value));
                        doubleFreqMap.put(count,doubleFreqMap.get(count)+1);
                        break;
                }

                columnList.add(colArray[1].substring(0,value.length()-1));
               // System.exit(1);
                count++;
            }
        }
        if(flag == false){
            RulesColumnInfo.createTable(map);
        }
        rowFlag=flag;
        return columnList;
    }

    public void printSet() throws FileNotFoundException {
    	
        Set<Integer> integerKeySet=integerSetMap.keySet();
        Set<Integer> stringKeySet= stringSetMap.keySet();
        Set<Integer> doubleKeySet=doubleSetMap.keySet();
        //File file=new File("/paramters.txt");
        File file=new File("files/paramters.txt");
        for(Integer key:integerKeySet){
            Set<Integer> setOfValues=integerSetMap.get(key);
            List <Integer> listOfValues=new ArrayList<>(setOfValues);
            Collections.sort(listOfValues);
        }
    }
    public Map<Integer,Set<Integer>> getIntegerSetMap(){
        return integerSetMap;
    }
    public Map<Integer,Integer> getIntegerFreqMap(){
    	return integerFreqMap;
    }
    
    public Map<Integer,Set<Double>> getDoubleSetMap(){
        return doubleSetMap;
    }
    public Map<Integer,Integer> getDoubleFreqMap(){
    	return doubleFreqMap;
    }
    
    public Map<Integer,Set<String>> getStringSetMap(){
        return stringSetMap;
    }
    public Map<Integer,Integer> getStringFreqMap(){
    	return stringFreqMap;
    }
    
    public Map<Integer,String> getParameterMap(){
        return map;
    }
    public FindWeight getWeightObject(){return this.weight;}
}
