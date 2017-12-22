package test;

import java.io.ByteArrayOutputStream;

/**
 * Created by ajay on 2/7/2016.
 */
public class UseByteArray {
    public static void main(String [] args){
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        int i = 0;
        while(i!= 10){
            bs.write(i);
            i++;
        }
        System.out.println(bs.toString());

    }
}
