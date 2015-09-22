import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean OPEN = true;
    private final boolean BLOCKED = false;
    private boolean[] grid;
    private int N;
    private WeightedQuickUnionUF UF;
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) throws java.lang.IllegalArgumentException {
        if ( N <= 0 )
            throw new java.lang.IllegalArgumentException("N must be > 0");
        this.N=N;
        grid = new boolean[N*N+1];
        UF = new WeightedQuickUnionUF(N*N+1);
        for (int cell=1; cell<=N*N; cell++) {grid[cell] = BLOCKED;}
    };
    // validating i,j
    private void validate(int i,int j) throws java.lang.IndexOutOfBoundsException {
        if ( (i < 1) || (i > N) )
            throw new java.lang.IndexOutOfBoundsException("There is no such CELL("+i+","+j+") in given GRID("+N+","+N+")");
        if ( (j < 1) || (j > N) )
            throw new java.lang.IndexOutOfBoundsException("There is no such CELL("+i+","+j+") in given GRID("+N+","+N+")");
    }
    //Coordinates i,j converting to massive index
    private int index(int i, int j) {
        return ((i - 1) * N + j);
    }
    // open site (row i, column j) if it is not open already
    public void open(int i, int j)  {
        validate(i,j);
        if (grid[index(i,j)] == OPEN) return;
        else {
            grid[index(i,j)] = OPEN;
            if ( (i-1>0) && isOpen(i-1,j)) {UF.union(index(i,j),index(i-1,j));}   // union for upper cell
            if ( (i+1<=N) && isOpen(i+1,j)) {UF.union(index(i,j),index(i+1,j));}  // union for bottom cell
            if ( (j-1>0) && isOpen(i,j-1)) {UF.union(index(i,j),index(i,j-1));}   // union for left cell
            if ( (j+1<=N) && isOpen(i,j+1)) {UF.union(index(i,j),index(i,j+1));}  // union for right cell
        }
    };
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validate(i,j);
        return grid[index(i,j)];
    };

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validate(i,j);
        if (isOpen(i,j) && i == 1 && j<=N) return true;
        if (isOpen(i,j)) {
            for (int topsite=1; topsite<=N; topsite++) {
                if (UF.connected(index(i, j), topsite)) return true;
            }
            return false;
        }
        else return false;
    };

    // does the system percolate?
    public boolean percolates(){
        for (int k=1;k<=N;k++) {
            if (isOpen(1, k)) {
                for (int bottomsite = N * N - N + 1; bottomsite <= N * N; bottomsite++) {
                    if (UF.connected(index(1, k), bottomsite)) return true;
                }
            }
        }
        return false;
    }
}
