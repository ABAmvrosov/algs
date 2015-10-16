import edu.princeton.cs.algs4.StdOut;

/************************************************
 * Solution from book                           *
 ************************************************/
public class ex1_1_9 {
    public static void main(String[] args) {
        String s = "";
        int N = 27;
        for (int n = N; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        StdOut.println(s);
    }
}
