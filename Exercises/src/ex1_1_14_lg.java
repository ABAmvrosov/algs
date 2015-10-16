import edu.princeton.cs.algs4.StdOut;

/************************************************
 * *
 ************************************************/
public class ex1_1_14_lg {
    public static int lg(int N) {
        int lg = 0;
        while (N / 2 >= 1) {
            N = N / 2;
            lg++;
        }
        return lg;
    }
    public static void main(String[] args) {
        StdOut.println(lg(15));
        StdOut.println(lg(120));
    }
}

