import edu.princeton.cs.algs4.StdOut;

/************************************************
 * a) sqrt, Newton's method                     *
 * b) arithmetical progression A1=1, An=999, d=1*
 * c) N typo? solution with fragment j < i      *
 * c) Geometric progression A1=1, An=512, d=2   *
 ************************************************/
public class ex1_1_7 {
    public static void main(String[] args) {

        double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001) {
            t = (9.0/t + t) / 2.0;
        }
        StdOut.printf("%.5f\n", t);

        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        StdOut.println(sum);

        int sum2 = 1;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < i; j++) {
                sum2++;
            }
        }
        StdOut.println(sum2);


    }
}
