package fpgrowth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Kamran
 */
public class Main {

    static int threshold = 10000;
    //static String file = "G:\\projects\\meenakshi ma'am\\files\\data.dat"; // specify the path of the .dat file.
    static String file = "files\\data.dat"; // specify the path of the .dat file.

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        new FPGrowth(new File(file));
        System.out.println((System.currentTimeMillis() - start));
        System.out.println("Fp growth");
    }
}
