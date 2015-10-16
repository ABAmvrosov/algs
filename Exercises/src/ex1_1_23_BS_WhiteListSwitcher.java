import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ex1_1_23_BS_WhiteListSwitcher {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 0);
    }
    public static int rank(int key, int[] a, int lo, int hi, int deep) {
        for (int i = 0; i < deep; i++) {
            StdOut.print(" ");
        }
        StdOut.printf("%d %d %d\n", lo, hi, deep);
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if      (key < a[mid]) return rank(key, a, lo, mid - 1, ++deep);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, ++deep);
        else                   return mid;
    }

    public static void main(String[] args) {
        int[] whitelist;

    }
}
