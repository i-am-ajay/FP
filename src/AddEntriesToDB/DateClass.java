package AddEntriesToDB;

/**
 * Created by gaa8664 on 3/11/15.
 */
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class DateClass {
    public static void main(String [] args) throws ParseException {
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        System.out.println(date);
        SimpleDateFormat format=new SimpleDateFormat("dd:MM:YYYY");
        Date date1=format.parse("12:08:2015");
        System.out.println(date1);

    }
}
