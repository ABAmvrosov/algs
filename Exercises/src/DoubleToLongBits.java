import edu.princeton.cs.algs4.StdOut;

import static java.lang.Double.doubleToLongBits;

/************************************************
 * *
 ************************************************/
public class DoubleToLongBits {
    public static void main(String[] args) {
        double value = 0.15;
        long bits = doubleToLongBits(value);
        StdOut.println(bits);
        StdOut.println(bits >>> 32);
        int a = (int) (bits ^ (bits >>> 32));
        StdOut.println(a);
        String st;
    }
}
