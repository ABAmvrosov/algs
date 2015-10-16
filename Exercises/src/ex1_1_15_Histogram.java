import edu.princeton.cs.algs4.StdOut;

/************************************************
 * *
 ************************************************/
public class ex1_1_15_Histogram {
    public static int[] histogram(int[] a, int M) {
        int[] arrM = new int[M];
        int counter;
        for (int i = 0; i < M; i++) {
            counter = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) {
                    counter++;
                }
            }
            arrM[i] = counter;
        }
        return arrM;
    }
    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 3, 3, 4, 5, 5, 5, 5};
        int M = 8;
        int[] arrM = histogram(a, M);
        for (int i = 0; i < M; i++) {
            StdOut.println(arrM[i]);
        }
    }
}
