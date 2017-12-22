package sensitive.rule;

/**
 * Created by gaa8664 on 5/19/15.
 */
public class WeightForIntValues {

    /*higher range is value that you want to set at the peek, like if you want to set age 50 at the pick
    * then it will be higher value.*/
    public static int calculateWeight(int lowerRange,int higherRange,int value, double cv){
    	int criticalValue = (int) cv;
        int rangeValue=Math.round(higherRange-lowerRange)/10;
        int diff=Math.abs((criticalValue-value)/(rangeValue*10));
        return 1-diff;
    }
}
