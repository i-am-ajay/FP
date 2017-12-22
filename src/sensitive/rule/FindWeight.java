package sensitive.rule;

/**
 * Created by gaa8664 on 5/19/15.
 */
import AddEntriesToDB.ReadFile;

import java.io.*;
import java.util.*;

public class FindWeight {
    private static Map<String,String> finalMasterMap;
    private Map<String,String> masterMap;
    private Map<String,int[]> intRangeMap;
    private Map<String,double[]> doubleRangeMap;
    private WeightForIntValues weightInt;
    private WeightForStringValues weightString;
    private FileWriter intFileStream;
    private FileWriter floatFileStream;
    private FileWriter stringFileStream;
    {
        finalMasterMap=masterMap;
    }

    public void fillMap() {
        /*get the maps from the ReadFile class and find the weight of each values */
        ReadFile readFileObj = ReadFile.getObject();
        // map of columns with all integer values set
        Map<Integer, Set<Integer>> intMap = readFileObj.getIntegerSetMap();
        // map of columns with all double values set
        Map<Integer, Set<Double>> doubleMap = readFileObj.getDoubleSetMap();
        // map of string columns with all string values set
        Map<Integer, Set<String>> stringMap = readFileObj.getStringSetMap();
        // map contains col names as values against col number
        Map<Integer, String> paramMap = readFileObj.getParameterMap();
        masterMap=new HashMap<>();
        intRangeMap=new HashMap<>();
        doubleRangeMap=new HashMap<>();
        Set<Integer> intSet = intMap.keySet();
        Set<Integer> doubleSet = doubleMap.keySet();
        Set<Integer> stringSet = stringMap.keySet();
        /* weights will be added to property files so that we don't need to calculate the weight everytime.*/
        
        try {
            intFileStream = new FileWriter("files\\intFile.properties");
            floatFileStream = new FileWriter("files\\floatFile.properties");
            stringFileStream = new FileWriter("files\\stringFile.properties");

            for (int i : intSet) {
                String str = paramMap.get(i);
                List<Integer> tempIntSet = new ArrayList<Integer>(intMap.get(i));
                // add column names and there type as value.
                masterMap.put(str, "integer");
                // get lowest and highest bound.
                intRangeMap.put(str, new int[]{tempIntSet.get(0), tempIntSet.get(tempIntSet.size() - 1)});
                //System.out.println(i+" integer");
                intFileStream.write(str + "=\n");
                intFileStream.flush();

            }
            
            for (int i : doubleSet) {
                String str = paramMap.get(i);
                List<Double> tempDoubleSet = new ArrayList<Double>(doubleMap.get(i));
                masterMap.put(str, "Double");
                // get lowest and highest bound
                doubleRangeMap.put(str, new double[]{tempDoubleSet.get(0), tempDoubleSet.get(tempDoubleSet.size() - 1)});
                //System.out.println(i+" double");
                floatFileStream.write(str+"=\n");
                floatFileStream.flush();
            }
            
            for (int i : stringSet) {
                String str = paramMap.get(i);
                masterMap.put(str, "String");
                //System.out.println(i+"String");
                Set<String> setOfString=stringMap.get(i);
                for(String st:setOfString) {
                    stringFileStream.write(st + "=\n");
                    stringFileStream.flush();
                }
            }
            weightInt = new WeightForIntValues();
            weightString = new WeightForStringValues();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                intFileStream.close();
                floatFileStream.close();
                stringFileStream.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    public int[] getIntAttributeValue(String attribute){
        return intRangeMap.get(attribute);
    }
    public double[] getDoubleAttributeValue(String attribute){
        return doubleRangeMap.get(attribute);
    }
    public Map<String, String> getMasterMap(){
        return masterMap;
    }
}
