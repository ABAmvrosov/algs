import edu.princeton.cs.algs4.StdOut;

/************************************************
 * mystery1 is multiplying a * b                *
 * mystery2 is power a ^ b                      *
 ************************************************/
public class ex1_1_18_Mystery {
    public static int mystery1(int a, int b) {
        if (b == 0)     return 0;
        if (b % 2 == 0) return mystery1(a+a, b/2);
                        return mystery1(a+a, b/2) + a;
    }
    public static int mystery2(int a, int b) {
        if (b == 0)     return 1;
        if (b % 2 == 0) return mystery2(a * a, b / 2);
        return mystery2(a * a, b / 2) * a;
    }
    public static void main(String[] args) {
        StdOut.println(mystery1(3, 11));
        StdOut.println(mystery2(2, 25));
    }
}
