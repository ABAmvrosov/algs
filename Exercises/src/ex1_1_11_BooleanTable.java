import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/************************************************
 *  Program give result in this way:            *
 *  | * | * |   |   | * |   | * |   | * |   |   *
 *  | * |   |   |   | * |   | * | * | * |   |   *
 *  |   | * |   |   | * | * |   |   |   |   |   *
 *  |   | * | * |   | * | * |   |   | * |   |   *
 *  | * | * |   |   | * | * |   |   | * | * |   *
 *  |   |   | * | * | * |   |   | * |   | * |   *
 *  |   |   |   | * |   |   | * |   | * | * |   *
 *  |   |   | * | * |   | * | * |   |   |   |   *
 *  |   | * |   |   |   | * |   |   |   |   |   *
 *  |   | * |   | * | * | * |   | * | * |   |   *
 ************************************************/
public class ex1_1_11_BooleanTable {
    public static void main(String[] args) {
        int N = 10; // massive dimension
        boolean[][] arr = new boolean[N][N];
        // random massive of boolean;
        double x;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                x = StdRandom.uniform();
                if (x < 0.5) arr[i][j] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            if (i != 0) {
                StdOut.print(" | ");
                StdOut.println();
            }
            for (int j = 0; j < N; j++) {
                StdOut.print(" | ");
                if (arr[i][j]) StdOut.print("*");
                else           StdOut.print("-");
            }
            if (i == N-1) StdOut.print(" | ");
        }
    }
}
