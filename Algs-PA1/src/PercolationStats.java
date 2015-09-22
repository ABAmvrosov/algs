import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int T;
    private double[] counts;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (T <= 0) { throw new java.lang.IllegalArgumentException(); }
        if (N <= 0) { throw new java.lang.IllegalArgumentException(); }
        this.T = T;
        Percolation grid;
        counts = new double[T];
        int a = 0, i, j;
        for (int ExpNum = 0; ExpNum < T; ExpNum++) {
            grid = new Percolation(N);
            int count = 0;
            while (!grid.percolates()) {
                i = StdRandom.uniform(1, N+1);
                j = StdRandom.uniform(1, N+1);
                if (!grid.isOpen(i, j)) {
                    grid.open(i, j);
                    count++;
                }
            }
            counts[a++] = (double) count/(N*N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        double sum = 0.0;
        for (double z : counts) {
            sum += z;
        }
        return sum/T;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double ExpDev, SumExpDev = 0.0;
        for (double z : counts) {
            ExpDev = (z - mean())*(z - mean());
            SumExpDev += ExpDev;
        }
        return Math.sqrt((SumExpDev)/(T-1));
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96*stddev())/(Math.sqrt(T));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96*stddev())/(Math.sqrt(T));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats PS = new PercolationStats(N, T);
        StdOut.println("mean                             = " + PS.mean());
        StdOut.println("stddev                           = " + PS.stddev());
        StdOut.println("95% confidence interval          = " + PS.confidenceLo() + "," + PS.confidenceHi());
    }
}
