package test;

import AddEntriesToDB.RulesColumnInfo;
import gui.RunTasks;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaa8664 on 6/17/15.
 */
public class Test {
    static int i = 8;
    static int j = 0;
    int n_i = 7;
    int n_j = 0;
    static
    {
        System.out.println("This is my static block");
        System.out.println("Value of i is "+i);
        j = 10;
        System.out.println("Value of j is "+j);
    }
    {
        System.out.println("Value of non static i is "+n_i);
        System.out.println("Value of non static j is "+n_j);
    }
    public Test(){
        n_i = 11;
        n_j = 15;
    }
    public static void main(String [] args){
       //System.out.println( RunTasks.binding.getValue());
       /* Map<String,Boolean> map = new HashMap<>();
        map.put("Sal",false);
        map.put("age",false);
        map.put("income",false);
        map.put("status",false);
        RulesColumnInfo.rulesMappingMap = map;
        String rule = "Sal=1000 status=yes";
        RulesColumnInfo.ruleToColMapping(rule);*/
        new Test();
    }
}
