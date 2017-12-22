package AddEntriesToDB;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by gaa8664 on 3/2/15.
 */
public class ProcessRecord {
    public List<String> processRecord(String str){
        StringTokenizer tokenizer=new StringTokenizer(str," ");
        List<String> list=new ArrayList<>();
        while(tokenizer.hasMoreElements()){
            String token=tokenizer.nextToken();
            StringTokenizer processToken= new StringTokenizer(token,"=");
            processToken.nextToken();
            String [] stringArray=processToken.nextToken().split("'");
            list.add(stringArray[0]);
        }
        return list;
    }
}
