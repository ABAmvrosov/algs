import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new java.lang.IllegalArgumentException("N must be > 0");
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N+2);
        for (int i = 0; i <= N; i++) { uf.union(0, i); }               // top-site
        for (int i = N*N-N+1; i <= N*N+1; i++) { uf.union(N*N+1, i); } // bottom-site
    }

    // validating i,j
    private void validate(int i, int j) {
        if ((i < 1) || (i > grid.length))
            throw new java.lang.IndexOutOfBoundsException("There is no such CELL");
        if ((j < 1) || (j > grid.length))
            throw new java.lang.IndexOutOfBoundsException("There is no such CELL");
    }

    //Coordinates i,j converting to massive index
    private int index(int i, int j) {
        return ((i - 1) * grid.length + j);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        validate(i, j);
        if (!grid[i-1][j-1]) {
            grid[i-1][j-1] = true;
            if ((i > 1) && isOpen(i-1, j)) { uf.union(index(i, j), index(i-1, j)); }            // union for upper cell
            if ((i < grid.length) && isOpen(i+1, j)) { uf.union(index(i, j), index(i+1, j)); }  // union for bottom cell
            if ((j > 1) && isOpen(i, j-1)) { uf.union(index(i, j), index(i, j-1)); }            // union for left cell
            if ((j < grid.length) && isOpen(i, j+1)) { uf.union(index(i, j), index(i, j+1)); }  // union for right cell
        }
    };

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i-1][j-1];
    };

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validate(i, j);
        return isOpen(i, j) && uf.connected(index(i, j), 0);
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) { return isOpen(1,1); }
        else { return uf.connected(0, grid.length*grid.length-1); }
    }
    public static void main(String[] args) {
        Percolation perc = new Percolation(10);
        perc.open(1, 1);
        perc.open(1, 2);
        StdOut.print(perc.uf.connected(1, 2));
    }
}
