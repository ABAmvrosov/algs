import edu.princeton.cs.algs4.StdOut;

/************************************************
 * *
 ************************************************/
public class ex1_1_13_Tansposition2D {
    public static void main(String[] args) {
        int M = 10; // raw
        int N = 5; // column
        int[][] arr = new int[M][N];
        int value = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = value++;
            }
        }
        // original
        for (int i = 0; i < M; i++) {
            StdOut.println();
            for (int j = 0; j < N; j++) {
                StdOut.print(arr[i][j] + "  ");
                if (arr[i][j] < M) { StdOut.print(" "); }
            }
        }

        int[][] arrTr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arrTr[i][j] = arr[j][i];
            }
        }

        // Transposition
        for (int i = 0; i < N; i++) {
            StdOut.println();
            for (int j = 0; j < M; j++) {
                StdOut.print(arrTr[i][j] + "  ");
                if (arr[j][i] < N) { StdOut.print(" "); }
            }
        }
    }
}
