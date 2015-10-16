import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/************************************************
 * Tn = Tn-1 * 1.62, for N 58 is about 3000 sec *
 ************************************************/
public class ex1_1_19_Fibonacci {
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N-1) + F(N-2);
    }
    public static long F_array(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        long[] arr = new long[N];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < N; i++) {
            arr[i] = arr[i-2] + arr[i-1];
        }
        return arr[N-1];
    }
    public static void main(String[] args) {
        for (int N = 0; N < 100; N++) {
            Stopwatch sw = new Stopwatch();
            StdOut.print(N + " " + F_array(N));
            StdOut.println(" " + sw.elapsedTime());
        }
    }
}
